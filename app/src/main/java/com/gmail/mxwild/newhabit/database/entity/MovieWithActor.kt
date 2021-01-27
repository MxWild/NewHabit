package com.gmail.mxwild.newhabit.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieWithActor (
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "actorId",
        associateBy = Junction(MovieActorJoin::class)
    )
    val actors: List<ActorEntity>
)