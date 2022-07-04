package com.mergencer.rtlcase.network

import com.mergencer.rtlcase.data.model.WeatherReport
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(value = "data/2.5/onecall")
    suspend fun fetchWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String
    ) : WeatherReport
}