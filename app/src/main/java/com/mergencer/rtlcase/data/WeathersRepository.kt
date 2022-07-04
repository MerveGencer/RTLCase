package com.mergencer.rtlcase.data

import com.mergencer.rtlcase.data.model.UserPlace
import com.mergencer.rtlcase.data.model.WeatherReport
import kotlinx.coroutines.flow.Flow

interface WeathersRepository {
    val weatherReports: Flow<List<WeatherReport>>
    suspend fun fetchUserPlaces(): List<UserPlace>?
    suspend fun fetchWeatherReports()
    suspend fun insertUserPlace(userPlace: UserPlace)
    suspend fun deleteWeatherReport(placeId: String)
}