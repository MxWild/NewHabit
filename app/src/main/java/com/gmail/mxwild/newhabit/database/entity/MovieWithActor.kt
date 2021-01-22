package com.gmail.mxwild.newhabit.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithActor (
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_movie_id"
    )
    val actors: List<ActorEntity>
)