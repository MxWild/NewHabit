package com.gmail.mxwild.newhabit.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Genre(
    val id: Int,
    val name: String
) : Parcelable