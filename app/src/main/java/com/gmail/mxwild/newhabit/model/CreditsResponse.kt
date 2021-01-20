package com.gmail.mxwild.newhabit.model

import com.gmail.mxwild.newhabit.model.dto.ActorDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("cast")
    val cast: List<ActorDto>,
)
