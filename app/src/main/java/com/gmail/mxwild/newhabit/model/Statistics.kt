package com.gmail.mxwild.newhabit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistics(val name: String, val count: Int) : Parcelable {
}