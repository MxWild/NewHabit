package com.gmail.mxwild.newhabit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.mxwild.newhabit.database.dao.ActorDao
import com.gmail.mxwild.newhabit.database.dao.GenreDao
import com.gmail.mxwild.newhabit.database.dao.MovieDao
import com.gmail.mxwild.newhabit.database.entity.*

@Database(
    entities = [
        ActorEntity::class,
        GenreEntity::class,
        MovieEntity::class,
        MovieGenreJoin::class,
        MovieActorJoin::class], version = 1, exportSchema = false
)
abstract class NewHabitDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun genreDao(): GenreDao
}