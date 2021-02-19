package com.gmail.mxwild.newhabit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie",
    indices = [
        Index(value = ["movieId"]),
        Index(value = ["number_of_ratings"])
    ]
)
data class MovieEntity(
    @PrimaryKey
    val movieId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "backdrop")
    val backdrop: String,
    @ColumnInfo(name = "ratings")
    val ratings: Float,
    @ColumnInfo(name = "number_of_ratings")
    val numberOfRatings: Int,
    @ColumnInfo(name = "minimum_age")
    val minimumAge: Int,
    @ColumnInfo(name = "runtime")
    val runtime: Int?
)