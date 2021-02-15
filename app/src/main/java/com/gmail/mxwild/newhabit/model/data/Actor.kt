package com.gmail.mxwild.newhabit.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Actor(
    val id: Int,
    val name: String,
    val picture: String?
) : Parcelable