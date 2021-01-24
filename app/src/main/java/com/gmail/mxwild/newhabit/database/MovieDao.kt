package com.gmail.mxwild.newhabit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.mxwild.newhabit.database.entity.MovieEntity
import com.gmail.mxwild.newhabit.database.entity.MovieWithGenre

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getMovieWithGenre(): List<MovieWithGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

}