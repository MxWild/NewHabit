package com.gmail.mxwild.newhabit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gmail.mxwild.newhabit.database.entity.MovieGenreJoin

@Dao
interface MovieGenreJoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(join: MovieGenreJoin)
}