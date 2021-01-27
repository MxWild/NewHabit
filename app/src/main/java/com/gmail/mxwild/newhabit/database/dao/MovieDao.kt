package com.gmail.mxwild.newhabit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.mxwild.newhabit.database.entity.MovieEntity
import com.gmail.mxwild.newhabit.database.entity.MovieWithGenres

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getMovieWithGenres(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)
}