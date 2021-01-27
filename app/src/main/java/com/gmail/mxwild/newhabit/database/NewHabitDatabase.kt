package com.gmail.mxwild.newhabit.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.mxwild.newhabit.App
import com.gmail.mxwild.newhabit.database.dao.*
import com.gmail.mxwild.newhabit.database.entity.*

@Database(entities = [
    ActorEntity::class,
    GenreEntity::class,
    MovieEntity::class,
    MovieGenreJoin::class,
    MovieActorJoin::class], version = 1)
abstract class NewHabitDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun genreDao(): GenreDao
    abstract fun movieWithGenre(): MovieGenreJoinDao
    abstract fun movieWithActor(): MovieActorJoinDao

    companion object {

        private const val DB_NAME = "movie.db"

        @Volatile
        private var INSTANCE: NewHabitDatabase? = null

        fun getDatabase(): NewHabitDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    App.getContext(),
                    NewHabitDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}