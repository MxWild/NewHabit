package com.gmail.mxwild.newhabit.repository

import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.database.NewHabitDatabase
import com.gmail.mxwild.newhabit.database.entity.ActorEntity
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Actor
import com.gmail.mxwild.newhabit.services.NetworkService

class ActorRepository {

    private val theMovieDbApi: TheMovieDbApi = NetworkService.MOVIE_API
    private val database = NewHabitDatabase.getDatabase()

    suspend fun getActors(movieId: Int): List<Actor> {
        return theMovieDbApi.getActors(movieId)
            .cast
            .map { actorDto -> DtoMapper.convertActorFromDto(actorDto) }
    }

    suspend fun getActorFromDB(movieId: Int): List<Actor> {
        return database.actorDao().getByMovieId(movieId).map {
            convertActorEntityToActor(it)
        }
    }

    private fun convertActorEntityToActor(actorEntity: ActorEntity) = Actor(
        id = actorEntity.id,
        name = actorEntity.name,
        picture = actorEntity.picture
    )

    suspend fun saveActors(actors: List<Actor>?, movieId: Int) {
        if (actors != null) {
            database.actorDao().insertAll(actors.map {
                convertActorToActorEntity(it, movieId)
            })
        }
    }

    private fun convertActorToActorEntity(actor: Actor, movieId: Int) = ActorEntity(
        id = actor.id,
        name = actor.name,
        picture = actor.picture,
        movieId = movieId
    )

}