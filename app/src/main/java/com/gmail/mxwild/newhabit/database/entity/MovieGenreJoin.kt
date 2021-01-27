package com.gmail.mxwild.newhabit.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["movieId", "genreId"],
    indices = [
        Index(value = ["movieId"]),
        Index(value = ["genreId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"]
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["genreId"],
            childColumns = ["genreId"]
        )],
    tableName = "MovieWithGenre"
)
data class MovieGenreJoin(
    val movieId: Int,
    val genreId: Int
)