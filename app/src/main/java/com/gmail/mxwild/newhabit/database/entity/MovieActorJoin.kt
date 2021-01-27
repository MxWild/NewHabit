package com.gmail.mxwild.newhabit.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["movieId", "actorId"],
    indices = [
        Index(value = ["movieId"]),
        Index(value = ["actorId"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"]
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["actorId"],
            childColumns = ["actorId"]
        )],
    tableName = "MovieWithActor"
)
data class MovieActorJoin(
    val movieId: Int,
    val actorId: Int
)
