package com.gmail.mxwild.newhabit.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdropImg: String?,
    @SerialName("runtime")
    var runtime: Int? = null,
    @SerialName("genres")
    var genres: List<GenreDto>? = null,
    @SerialName("vote_average")
    val rating: Float,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("adult")
    val adult: Boolean
)
