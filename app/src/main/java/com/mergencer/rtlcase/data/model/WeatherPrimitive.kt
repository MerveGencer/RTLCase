package com.mergencer.rtlcase.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherPrimitive(
    @Json(name = "dt")
    val currentTime: Long,
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "weather")
    val weatherDescription: List<WeatherDescription>,
): Parcelable