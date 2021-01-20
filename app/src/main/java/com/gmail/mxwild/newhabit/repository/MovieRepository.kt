package com.gmail.mxwild.newhabit.repository

import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.services.NetworkService

class MovieRepository {

    private val theMovieDbApi: TheMovieDbApi = NetworkService.MOVIE_API

    suspend fun getMovies(): List<Movie> {
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

}