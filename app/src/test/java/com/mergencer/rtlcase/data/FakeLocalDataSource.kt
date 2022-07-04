package com.mergencer.rtlcase.data

import com.mergencer.rtlcase.data.local.WeatherLocalDataSource
import com.mergencer.rtlcase.data.model.UserPlace
import com.mergencer.rtlcase.data.model.WeatherReport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLocalDataSource(
    private var weatherReports: MutableList<WeatherReport>? = mutableListOf(),
    private var userPlaces: MutableList<UserPlace>? = mutableListOf()
): WeatherLocalDataSource {

    override suspend fun getUserPlaces(): List<UserPlace>? {
        return userPlaces
    }

    override suspend fun getUserPlaceById(placeId: String): UserPlace? {
        return userPlaces?.find { it.id == placeId }
    }

    override fun getWeatherReports(): Flow<List<WeatherReport>> {
        weatherReports?.let {
            return MutableStateFlow(it.toList())
        }
        return MutableStateFlow(listOf())
    }

    override suspend fun insertUserPlace(userPlace: UserPlace) {
        if (userPlaces == null) {
            userPlaces = mutableListOf()
        }
        userPlaces!!.add(userPlace)
    }

    override suspend fun insertWeatherReport(weatherReport: WeatherReport) {
        if (weatherReports == null) weatherReports = mutableListOf()
        weatherReports!!.add(weatherReport)
    }

    override suspend fun deleteWeatherReport(placeId: String) {
        weatherReports?.removeIf{it.id == placeId}
    }

    override suspend fun deleteUserPlace(placeId: String) {
        userPlaces?.removeIf{it.id == placeId}
    }

}