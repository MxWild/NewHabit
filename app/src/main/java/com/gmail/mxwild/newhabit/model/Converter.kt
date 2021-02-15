package com.gmail.mxwild.newhabit.model

import com.gmail.mxwild.newhabit.database.entity.GenreEntity
import com.gmail.mxwild.newhabit.database.entity.MovieEntity
import com.gmail.mxwild.newhabit.database.entity.MovieWithGenres
import com.gmail.mxwild.newhabit.model.data.Genre
import com.gmail.mxwild.newhabit.model.data.Movie

class Converter {

    companion object {

        fun convertMovieEntityToMovie(movieEntity: MovieWithGenres) = Movie(
            id = movieEntity.movie.movieId,
            title = movieEntity.movie.title,
            overview = movieEntity.movie.overview,
            poster = movieEntity.movie.poster,
            backdrop = movieEntity.movie.backdrop,
            ratings = movieEntity.movie.ratings,
            numberOfRatings = movieEntity.movie.numberOfRatings,
            minimumAge = movieEntity.movie.minimumAge,
            runtime = movieEntity.movie.runtime,
            genres = movieEntity.genres.map { convertGenreEntityToGenre(it) },
            actors = emptyList()
        )

        fun convertMovieToMovieEntity(movie: Movie) = MovieEntity(
            movieId = movie.id,
            title = movie.title,
            overview = movie.overview,
            poster = movie.poster,
            backdrop = movie.backdrop,
            ratings = movie.ratings,
            numberOfRatings = movie.numberOfRatings,
            minimumAge = movie.minimumAge,
            runtime = movie.runtime
        )

        private fun convertGenreEntityToGenre(genreEntity: GenreEntity) = Genre(
            id = genreEntity.genreId,
            name = genreEntity.name
        )

        fun convertToGenreEntity(genre: Genre) = GenreEntity(
            genreId = genre.id,
            name = genre.name
        )
    }
}