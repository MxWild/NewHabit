package com.gmail.mxwild.newhabit.database.dao

import androidx.room.*
import com.gmail.mxwild.newhabit.database.entity.MovieActorJoin
import com.gmail.mxwild.newhabit.database.entity.MovieEntity
import com.gmail.mxwild.newhabit.database.entity.MovieGenreJoin
import com.gmail.mxwild.newhabit.database.entity.MovieWithGenres

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY ratings DESC")
    suspend fun getMovieWithGenres(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieWithGenre(join: MovieGenreJoin)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieWithActors(join: MovieActorJoin)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Update
    suspend fun updateAll(map: List<MovieEntity>)
}