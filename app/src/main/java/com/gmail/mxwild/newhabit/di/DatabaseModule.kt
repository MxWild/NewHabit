package com.gmail.mxwild.newhabit.di

import android.content.Context
import androidx.room.Room
import com.gmail.mxwild.newhabit.database.NewHabitDatabase
import com.gmail.mxwild.newhabit.database.dao.ActorDao
import com.gmail.mxwild.newhabit.database.dao.GenreDao
import com.gmail.mxwild.newhabit.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "movie.db"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewHabitDatabase {
        return Room.databaseBuilder(
            context,
            NewHabitDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: NewHabitDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    @Singleton
    fun provideGenreDao(database: NewHabitDatabase): GenreDao {
        return database.genreDao()
    }

    @Provides
    @Singleton
    fun provideActorDao(database: NewHabitDatabase): ActorDao {
        return database.actorDao()
    }

}