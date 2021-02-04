package com.gmail.mxwild.newhabit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.mxwild.newhabit.database.entity.ActorEntity

@Dao
interface ActorDao {

    @Query(
        """SELECT * FROM actor 
        INNER JOIN MovieWithActor ON actor.actorId = MovieWithActor.actorId 
        WHERE MovieWithActor.movieId = :movieId"""
    )
    suspend fun getActorsByMovieId(movieId: Int): List<ActorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actors: List<ActorEntity>)
}