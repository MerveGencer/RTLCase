package com.mergencer.rtlcase.data.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class Converters(private val moshi: Moshi) {

    @TypeConverter
    fun fromWeatherToString(data: Weather?): String? {
        return moshi.adapter(Weather::class.java).toJson(data)
    }

    @TypeConverter
    fun fromStringToWeather(json: String?): Weather? {
        json?.let {
            return moshi.adapter(Weather::class.java).fromJson(json)
        }
        return null
    }

    @TypeConverter
    fun fromWeatherListToString(data: List<Weather>?): String? {
        val type = Types.newParameterizedType(List::class.java, Weather::class.java)
        return moshi.adapter<List<Weather>>(type).toJson(data)
    }

    @TypeConverter
    fun fromStringToWeatherList(json: String?): List<Weather>? {
        json?.let {
            val type = Types.newParameterizedType(List::class.java, Weather::class.java)
            return  moshi.adapter<List<Weather>>(type).fromJson(json)
        }
        return null
    }

    @TypeConverter
    fun fromAlertToString(data: Alert?): String? {
        return moshi.adapter(Alert::class.java).toJson(data)
    }

    @TypeConverter
    fun fromStringToAlert(json: String?): Alert? {
        json?.let {
            return moshi.adapter(Alert::class.java).fromJson(json)
        }
        return null
    }

    @TypeConverter
    fun fromAlertListToString(data: List<Alert>?): String? {
        val type = Types.newParameterizedType(List::class.java, Alert::class.java)
        return moshi.adapter<List<Alert>>(type).toJson(data)
    }

    @TypeConverter
    fun fromStringToAlertList(json: String?): List<Alert>? {
        json?.let {
            val type = Types.newParameterizedType(List::class.java, Alert::class.java)
            return  moshi.adapter<List<Alert>>(type).fromJson(json)
        }
        return null
    }

    @TypeConverter
    fun fromWeatherPrimitiveToString(data: WeatherPrimitive?): String? {
        return moshi.adapter(WeatherPrimitive::class.java).toJson(data)
    }


    @TypeConverter
    fun fromStringToWeatherPrimitive(json: String?): WeatherPrimitive? {
        json?.let {
            return moshi.adapter(WeatherPrimitive::class.java).fromJson(json)
        }
        return null
    }

    @TypeConverter
    fun fromUserPlaceToString(data: UserPlace?): String? {
        return moshi.adapter(UserPlace::class.java).toJson(data)
    }

    @TypeConverter
    fun fromUserPlaceListToString(data: List<UserPlace>?): String? {
        val type = Types.newParameterizedType(List::class.java, UserPlace::class.java)
        return moshi.adapter<List<UserPlace>>(type).toJson(data)
    }

    @TypeConverter
    fun fromStringToUserPlace(json: String?): UserPlace? {
        json?.let {
            return moshi.adapter(UserPlace::class.java).fromJson(json)
        }
        return null
    }

    @TypeConverter
    fun fromStringToUserPlaceList(json: String?): List<UserPlace>? {
        json?.let {
            val type = Types.newParameterizedType(List::class.java, UserPlace::class.java)
            return  moshi.adapter<List<UserPlace>>(type).fromJson(json)
        }
        return null
    }
}