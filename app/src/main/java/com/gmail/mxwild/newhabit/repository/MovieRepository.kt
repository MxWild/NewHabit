package com.gmail.mxwild.newhabit.repository

import android.util.Log
import com.gmail.mxwild.newhabit.App
import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.database.NewHabitDatabase
import com.gmail.mxwild.newhabit.database.entity.GenreEntity
import com.gmail.mxwild.newhabit.database.entity.MovieEntity
import com.gmail.mxwild.newhabit.database.entity.MovieGenreJoin
import com.gmail.mxwild.newhabit.database.entity.MovieWithGenres
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Genre
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.services.NetworkService

class MovieRepository {

    private val theMovieDbApi: TheMovieDbApi = NetworkService.MOVIE_API
    private val database = NewHabitDatabase.getDatabase()

    suspend fun getMovies(isReload: Boolean, isBackground: Boolean): List<Movie> {
        var allMovieFromDB: List<Movie> = emptyList()

        if (!isReload && !isBackground) {
            allMovieFromDB = getAllMovieFromDB()
        }

        return if (allMovieFromDB.isNotEmpty()) {
            allMovieFromDB
        } else {
            val moviesFromNetwork = getMoviesFromNetwork(isBackground)
            saveMovies(moviesFromNetwork)
            moviesFromNetwork
        }
    }

    private suspend fun getMoviesFromNetwork(isBackground: Boolean): List<Movie> {

        val movies = mutableListOf<Movie>()
        var startPage = 1L
        var endPage = App.pageCounter.get()

        if (!isBackground) {
            val topRatedResponse = theMovieDbApi.getTopRated()
            startPage = App.pageCounter.get()
            endPage = if (startPage + 5 <= topRatedResponse.totalPages) startPage + 5
            else topRatedResponse.totalPages
        }

        Log.i(TAG, "pageCounter before := ${App.pageCounter.get()}")

        for (page in startPage..endPage) {
            val topRatedMovies = theMovieDbApi
                .getTopRated(page)
                .results
                .map { it.id }

            movies.addAll(topRatedMovies.distinct().map {
                DtoMapper.convertMovieFromDto(
                    theMovieDbApi.getMovieById(it)
                )
            })
        }

        App.pageCounter.set(endPage)
        Log.i(TAG, "pageCounter after := ${App.pageCounter.get()}")
        return movies
    }

    private suspend fun getAllMovieFromDB(): List<Movie> {
        return database.movieDao().getMovieWithGenres().map {
            convertMovieEntityToMovie(it)
        }
    }

    private suspend fun saveMovies(movies: List<Movie>?) {
        if (movies != null) {
            database.movieDao().insertAll(movies.map { convertMovieToMovieEntity(it) })

            movies.forEach { movie ->
                movie.genres?.let { it ->
                    database.genreDao().insertAll(it.map {
                        convertToGenreEntity(it)
                    })
                }
                if (!movie.genres.isNullOrEmpty()) {
                    val map = movie.genres.map { genre -> MovieGenreJoin(movie.id, genre.id) }
                    database.movieDao().insertAllMovieWithGenres(map)

                    /*for (genre in movie.genres) {
                        database.movieDao()
                            .insertMovieWithGenre(MovieGenreJoin(movie.id, genre.id))
                    }*/
                }
            }
        }
    }

    suspend fun update(movies: List<Movie>) {
        database.movieDao().updateAll(movies.map { convertMovieToMovieEntity(it) })
    }

    private fun convertMovieEntityToMovie(movieEntity: MovieWithGenres) = Movie(
        id = movieEntity.movie.movieId,
        title = movieEntity.movie.title,
        overview = movieEntity.movie.overview,
        poster = movieEntity.movie.poster,
        backdrop = movieEntity.movie.backdrop,
        ratings = movieEntity.movie.ratings,
        numberOfRatings = movieEntity.movie.numberOfRatings,
        minimumAge = movieEntity.movie.minimumAge,
        runtime = movieEntity.movie.runtime,
        genres = movieEntity.genres.map { convertGenreEntityToGenre(it) },
        actors = emptyList()
    )

    private fun convertMovieToMovieEntity(movie: Movie) = MovieEntity(
        movieId = movie.id,
        title = movie.title,
        overview = movie.overview,
        poster = movie.poster,
        backdrop = movie.backdrop,
        ratings = movie.ratings,
        numberOfRatings = movie.numberOfRatings,
        minimumAge = movie.minimumAge,
        runtime = movie.runtime
    )

    private fun convertGenreEntityToGenre(genreEntity: GenreEntity) = Genre(
        id = genreEntity.genreId,
        name = genreEntity.name
    )

    private fun convertToGenreEntity(genre: Genre) = GenreEntity(
        genreId = genre.id,
        name = genre.name
    )

    companion object {
        private const val TAG = "MovieRepository"
    }

}