package com.gmail.mxwild.newhabit.api

import com.gmail.mxwild.newhabit.model.CreditsResponse
import com.gmail.mxwild.newhabit.model.MovieResponse
import com.gmail.mxwild.newhabit.model.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("page") page: Long = 1): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path(value = "movie_id") id: Int): MovieDto

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(@Path(value = "movie_id") id: Int): CreditsResponse

}