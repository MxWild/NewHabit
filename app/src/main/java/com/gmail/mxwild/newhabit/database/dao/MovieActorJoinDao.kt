package com.gmail.mxwild.newhabit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.mxwild.newhabit.database.entity.ActorEntity
import com.gmail.mxwild.newhabit.database.entity.MovieActorJoin

@Dao
interface MovieActorJoinDao {

    @Query(
        """SELECT * FROM actor 
        INNER JOIN MovieWithActor ON actor.actorId = MovieWithActor.actorId 
        WHERE MovieWithActor.movieId = :movieId"""
    )
    suspend fun getActorsByMovieId(movieId: Int): List<ActorEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(join: MovieActorJoin)
}