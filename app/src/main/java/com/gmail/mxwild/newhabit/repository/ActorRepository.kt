package com.gmail.mxwild.newhabit.repository

import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.database.NewHabitDatabase
import com.gmail.mxwild.newhabit.database.entity.ActorEntity
import com.gmail.mxwild.newhabit.database.entity.MovieActorJoin
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Actor
import com.gmail.mxwild.newhabit.services.NetworkService

class ActorRepository {

    private val theMovieDbApi: TheMovieDbApi = NetworkService.MOVIE_API
    private val database = NewHabitDatabase.getDatabase()

    suspend fun getActors(movieId: Int): List<Actor> {
        val actorsFromDB = getActorFromDB(movieId)

        return if (actorsFromDB.isNotEmpty()) {
            actorsFromDB
        } else {
            val actorsFromNetwork = getActorsFromNetwork(movieId)
            saveActors(actorsFromNetwork, movieId)
            return actorsFromNetwork
        }
    }

    private suspend fun getActorsFromNetwork(movieId: Int): List<Actor> {
        return theMovieDbApi.getActors(movieId)
            .cast
            .map { actorDto -> DtoMapper.convertActorFromDto(actorDto) }
    }

    private suspend fun getActorFromDB(movieId: Int): List<Actor> {
        return database.actorDao().getActorsByMovieId(movieId).map {
            convertActorEntityToActor(it)
        }
    }

    private suspend fun saveActors(actors: List<Actor>?, movieId: Int) {
        if (actors != null) {
            database.actorDao().insertAll(actors.map {
                convertActorToActorEntity(it)
            })
            for (actor in actors) {
                database.movieDao()
                    .insertMovieWithActors(MovieActorJoin(movieId, actor.id))
            }
        }
    }

    private fun convertActorEntityToActor(actorEntity: ActorEntity) = Actor(
        id = actorEntity.actorId,
        name = actorEntity.name,
        picture = actorEntity.picture
    )

    private fun convertActorToActorEntity(actor: Actor) = ActorEntity(
        actorId = actor.id,
        name = actor.name,
        picture = actor.picture
    )

}