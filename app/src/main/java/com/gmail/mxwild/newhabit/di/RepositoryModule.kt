package com.gmail.mxwild.newhabit.di

import com.gmail.mxwild.newhabit.api.TheMovieDbApi
import com.gmail.mxwild.newhabit.database.dao.ActorDao
import com.gmail.mxwild.newhabit.database.dao.GenreDao
import com.gmail.mxwild.newhabit.database.dao.MovieDao
import com.gmail.mxwild.newhabit.repository.ActorRepository
import com.gmail.mxwild.newhabit.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideMovieRepository(
        movieDao: MovieDao,
        genreDao: GenreDao,
        theMovieDbApi: TheMovieDbApi
    ): MovieRepository {
        return MovieRepository(movieDao, genreDao, theMovieDbApi)
    }

    @Provides
    fun provideActorRepository(
        movieDao: MovieDao,
        actorDao: ActorDao,
        theMovieDbApi: TheMovieDbApi
    ): ActorRepository {
        return ActorRepository(movieDao, actorDao, theMovieDbApi)
    }
}