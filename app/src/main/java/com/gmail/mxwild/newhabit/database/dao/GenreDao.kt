package com.gmail.mxwild.newhabit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.gmail.mxwild.newhabit.database.entity.GenreEntity

@Dao
interface GenreDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(genres: List<GenreEntity>)
}