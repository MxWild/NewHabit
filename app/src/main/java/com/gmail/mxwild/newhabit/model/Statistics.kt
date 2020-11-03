package com.gmail.mxwild.newhabit.model

import android.os.Parcel
import android.os.Parcelable

class Statistics(val name: String?, val count: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Statistics> {
        override fun createFromParcel(parcel: Parcel): Statistics {
            return Statistics(parcel)
        }

        override fun newArray(size: Int): Array<Statistics?> {
            return arrayOfNulls(size)
        }
    }
}