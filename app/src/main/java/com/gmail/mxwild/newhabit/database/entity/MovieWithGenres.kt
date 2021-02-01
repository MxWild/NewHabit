package com.gmail.mxwild.newhabit.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithGenres(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreJoin::class)
    )
    val genres: List<GenreEntity>
)
