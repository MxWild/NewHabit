package com.gmail.mxwild.newhabit.model

import com.gmail.mxwild.newhabit.BuildConfig.POSTER_PATH
import com.gmail.mxwild.newhabit.model.data.Actor
import com.gmail.mxwild.newhabit.model.data.Genre
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.model.dto.ActorDto
import com.gmail.mxwild.newhabit.model.dto.GenreDto
import com.gmail.mxwild.newhabit.model.dto.MovieDto

object DtoMapper {

    fun convertMovieFromDto(movieDto: MovieDto, actors: CreditsResponse): Movie {
        return Movie(
            id = movieDto.id,
            title = movieDto.title,
            poster = "${POSTER_PATH}${movieDto.poster}",
            overview = movieDto.overview,
            backdrop = "${POSTER_PATH}${movieDto.backdropImg}",
            ratings = movieDto.rating,
            numberOfRatings = movieDto.voteCount,
            minimumAge = if (movieDto.adult) 16 else 13,
            runtime = movieDto.runtime,
            genres = movieDto.genres?.map { convertGenreFromDto(it) },
            actors = actors.cast
                .filter { it.profileImg != null }
                .map { convertActorFromDto(it) }
        )
    }

    private fun convertGenreFromDto(genreDto: GenreDto) = Genre(
        id = genreDto.id,
        name = genreDto.name
    )


    private fun convertActorFromDto(actorDto: ActorDto): Actor {
        return Actor(
            id = actorDto.id,
            name = actorDto.name,
            picture = actorDto.profileImg
        )
    }
}