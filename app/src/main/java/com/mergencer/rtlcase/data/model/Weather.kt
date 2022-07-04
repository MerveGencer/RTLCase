package com.mergencer.rtlcase.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    @Json(name = "dt")
    val currentTime: Long,
    val temp: Temperature,
    @Json(name = "feels_like")
    val feelsLike: Temperature,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "wind_deg")
    val windDegree: Int,
    @Json(name = "pop")
    val probabilityOfPrecipitation: Double,
    @Json(name = "rain")
    val probabilityOfRain: Double? = 0.0,
    @Json(name = "weather")
    val weatherDescription: List<WeatherDescription>,
): Parcelable