package com.gmail.mxwild.newhabit.repository

import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Actor
import com.gmail.mxwild.newhabit.services.NetworkService

class ActorRepository {

    private val theMovieDbApi: TheMovieDbApi = NetworkService.MOVIE_API

    suspend fun getActors(movieId: Int): List<Actor> {
        return theMovieDbApi.getActors(movieId)
            .cast
            .map { actorDto -> DtoMapper.convertActorFromDto(actorDto) }
    }

}