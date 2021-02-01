package com.gmail.mxwild.newhabit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gmail.mxwild.newhabit.database.entity.ActorEntity

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(actors: List<ActorEntity>)
}