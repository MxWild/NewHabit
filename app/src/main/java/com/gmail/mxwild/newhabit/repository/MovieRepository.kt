package com.gmail.mxwild.newhabit.repository

import android.util.Log
import com.gmail.mxwild.newhabit.App
import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.database.dao.GenreDao
import com.gmail.mxwild.newhabit.database.dao.MovieDao
import com.gmail.mxwild.newhabit.database.entity.MovieGenreJoin
import com.gmail.mxwild.newhabit.model.Converter.Companion.convertMovieEntityToMovie
import com.gmail.mxwild.newhabit.model.Converter.Companion.convertMovieToMovieEntity
import com.gmail.mxwild.newhabit.model.Converter.Companion.convertToGenreEntity
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val theMovieDbApi: TheMovieDbApi
) {

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
            endPage = if (startPage + HOW_MANY_PAGE_LOAD <= topRatedResponse.totalPages) startPage + HOW_MANY_PAGE_LOAD
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
        return movieDao.getMovieWithGenres().map {
            convertMovieEntityToMovie(it)
        }
    }

    private suspend fun saveMovies(movies: List<Movie>?) {
        if (movies != null) {
            movieDao.insertAll(movies.map { convertMovieToMovieEntity(it) })

            movies.forEach { movie ->
                movie.genres?.let { it ->
                    genreDao.insertAll(it.map {
                        convertToGenreEntity(it)
                    })
                }
                if (!movie.genres.isNullOrEmpty()) {
                    val movieGenreJoin =
                        movie.genres.map { genre -> MovieGenreJoin(movie.id, genre.id) }
                    movieDao.insertAllMovieWithGenres(movieGenreJoin)
                }
            }
        }


    }

    suspend fun update(movies: List<Movie>) {
        movieDao.updateAll(movies.map { convertMovieToMovieEntity(it) })
    }

    companion object {
        private const val TAG = "MovieRepository"
        private const val HOW_MANY_PAGE_LOAD = 3
    }

}