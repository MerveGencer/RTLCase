package com.mergencer.rtlcase.data.remote.impl

import com.mergencer.rtlcase.data.remote.WeatherRemoteDataSource
import com.mergencer.rtlcase.network.WeatherApiService
import javax.inject.Inject

class WeatherRemoteDataSourceImpl  @Inject constructor(
    private val apiService: WeatherApiService): WeatherRemoteDataSource {

    private val units: String = "metric"
    private val exclude: String = "minutely, hourly"
    private val apiKey: String = "2dd065ec9c6b666302c5ceeb3be78b2e"

    override suspend fun fetchWeatherReportByLocation(lat: Double, lng: Double)
        =  apiService.fetchWeather(lat, lng, units, exclude, apiKey)

}