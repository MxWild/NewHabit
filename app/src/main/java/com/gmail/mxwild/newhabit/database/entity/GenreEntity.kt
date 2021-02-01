package com.gmail.mxwild.newhabit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey
    val genreId: Int,
    @ColumnInfo(name = "name")
    val name: String
)