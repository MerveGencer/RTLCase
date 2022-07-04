package com.mergencer.rtlcase.data

import com.mergencer.rtlcase.data.model.WeatherReport
import com.mergencer.rtlcase.data.remote.WeatherRemoteDataSource

class FakeRemoteDataSource(
    private var weatherReports: MutableList<WeatherReport>? = mutableListOf()
): WeatherRemoteDataSource {

    override suspend fun fetchWeatherReportByLocation(lat: Double, lng: Double): WeatherReport? {
        return weatherReports?.find { it.lat == lat && it.lon == lng }
    }

}