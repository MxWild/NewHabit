package com.gmail.mxwild.newhabit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor")
data class ActorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "picture")
    val picture: String?,
    @ColumnInfo(name = "actor_movie_id")
    val movieId: Int
)