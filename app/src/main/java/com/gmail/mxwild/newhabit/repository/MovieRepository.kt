package com.gmail.mxwild.newhabit.repository

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

    suspend fun getMovies(reload: Boolean): List<Movie> {
        var allMovieFromDB: List<Movie> = emptyList()

        if (!reload) {
            allMovieFromDB = getAllMovieFromDB()
        }

        return if (allMovieFromDB.isNotEmpty()) {
            allMovieFromDB
        } else {
            val moviesFromNetwork = getMoviesFromNetwork()
            saveMovies(moviesFromNetwork)
            moviesFromNetwork
        }
    }

    private suspend fun getMoviesFromNetwork(): List<Movie> {
        val topRatedMovies = theMovieDbApi
            .getTopRated()
            .results
            .map { it.id }

        return topRatedMovies.distinct().map {
            DtoMapper.convertMovieFromDto(
                theMovieDbApi.getMovieById(it)
            )
        }
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
                    for (genre in movie.genres) {
                        database.movieWithGenre()
                            .insert(MovieGenreJoin(movie.id, genre.id))
                    }
                }
            }
        }
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

}