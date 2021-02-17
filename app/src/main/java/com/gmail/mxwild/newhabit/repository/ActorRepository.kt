package com.gmail.mxwild.newhabit.repository

import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.database.dao.ActorDao
import com.gmail.mxwild.newhabit.database.dao.MovieDao
import com.gmail.mxwild.newhabit.database.entity.MovieActorJoin
import com.gmail.mxwild.newhabit.model.Converter.Companion.convertActorEntityToActor
import com.gmail.mxwild.newhabit.model.Converter.Companion.convertActorToActorEntity
import com.gmail.mxwild.newhabit.model.DtoMapper
import com.gmail.mxwild.newhabit.model.data.Actor

class ActorRepository constructor(
    private val movieDao: MovieDao,
    private val actorDao: ActorDao,
    private val theMovieDbApi: TheMovieDbApi
) {

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
        return actorDao.getActorsByMovieId(movieId).map {
            convertActorEntityToActor(it)
        }
    }

    private suspend fun saveActors(actors: List<Actor>?, movieId: Int) {
        if (actors != null) {
            actorDao.insertAll(actors.map {
                convertActorToActorEntity(it)
            })
            for (actor in actors) {
                movieDao.insertMovieWithActors(MovieActorJoin(movieId, actor.id))
            }
        }
    }
}