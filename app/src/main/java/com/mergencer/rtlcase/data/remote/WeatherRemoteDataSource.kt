package com.mergencer.rtlcase.data.remote

import com.mergencer.rtlcase.data.model.WeatherReport

interface WeatherRemoteDataSource {
    suspend fun fetchWeatherReportByLocation(lat: Double, lng: Double): WeatherReport?
}
