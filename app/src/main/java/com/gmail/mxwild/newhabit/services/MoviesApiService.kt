package com.gmail.mxwild.newhabit.services

import com.gmail.mxwild.newhabit.BuildConfig.API_KEY
import com.gmail.mxwild.newhabit.model.CreditsResponse
import com.gmail.mxwild.newhabit.model.MovieResponse
import com.gmail.mxwild.newhabit.model.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {

    @GET("movie/top_rated?api_key=${API_KEY}")
    suspend fun getTopRated(): MovieResponse

    @GET("movie/{movie_id}?api_key=${API_KEY}")
    suspend fun getMovieById(@Path(value = "movie_id") id: Int): MovieDto

    @GET("movie/{movie_id}/credits?api_key=${API_KEY}")
    suspend fun getActors(@Path(value = "movie_id") id: Int): CreditsResponse

}