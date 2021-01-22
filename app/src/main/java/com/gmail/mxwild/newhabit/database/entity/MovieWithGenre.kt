package com.gmail.mxwild.newhabit.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithGenre(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_movie_id"
    )
    val genres: List<GenreEntity>
)
