package com.mergencer.rtlcase.data.local

import com.mergencer.rtlcase.data.model.UserPlace
import com.mergencer.rtlcase.data.model.WeatherReport
import kotlinx.coroutines.flow.Flow

interface WeatherLocalDataSource {

    suspend fun getUserPlaces(): List<UserPlace>?
    suspend fun getUserPlaceById(placeId: String): UserPlace?
    fun getWeatherReports(): Flow<List<WeatherReport>>
    suspend fun insertUserPlace(userPlace: UserPlace)
    suspend fun insertWeatherReport(weatherReport: WeatherReport)
    suspend fun deleteWeatherReport(placeId: String)
    suspend fun deleteUserPlace(placeId: String)

}
