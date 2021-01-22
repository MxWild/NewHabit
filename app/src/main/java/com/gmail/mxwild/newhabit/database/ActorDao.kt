package com.gmail.mxwild.newhabit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.mxwild.newhabit.database.entity.ActorEntity

@Dao
interface ActorDao {

    @Query("SELECT * FROM actor WHERE actor_movie_id = :movieId ORDER BY id ASC")
    suspend fun getByMovieId(movieId: Int): List<ActorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actors: List<ActorEntity>)
}