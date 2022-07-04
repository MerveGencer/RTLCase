package com.mergencer.rtlcase.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String): Parcelable
