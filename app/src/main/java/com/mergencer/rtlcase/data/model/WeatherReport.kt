package com.mergencer.rtlcase.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "weathers")
@Parcelize
data class WeatherReport(
    @PrimaryKey
    var id: String = "id",
    var placeId: String? = null,
    var placeName: String? = null,
    var placeAddress: String? = null,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val current: WeatherPrimitive,
    val daily: List<Weather>,
    val alerts: List<Alert>?,
): Parcelable