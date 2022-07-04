package com.mergencer.rtlcase.data.local.impl

import com.mergencer.rtlcase.data.UserPlacesDao
import com.mergencer.rtlcase.data.UserWeatherByLocationDao
import com.mergencer.rtlcase.data.local.WeatherLocalDataSource
import com.mergencer.rtlcase.data.model.UserPlace
import com.mergencer.rtlcase.data.model.WeatherReport
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val placesDao: UserPlacesDao,
    private val weatherDao: UserWeatherByLocationDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherLocalDataSource {

    override suspend fun getUserPlaces() = withContext(ioDispatcher) {
        placesDao.getUserPlaces()
    }

    override suspend fun getUserPlaceById(placeId: String) = withContext(ioDispatcher) {
        placesDao.getUserPlaceById(placeId)
    }

    override fun getWeatherReports(): Flow<List<WeatherReport>> {
        return weatherDao.getWeatherReports()
    }

    override suspend fun insertUserPlace(userPlace: UserPlace) = withContext(ioDispatcher) {
        placesDao.insert(userPlace)
    }

    override suspend fun insertWeatherReport(weatherReport: WeatherReport) = withContext(ioDispatcher) {
        weatherDao.insert(weatherReport)
    }

    override suspend fun deleteWeatherReport(placeId: String) {
        weatherDao.delete(placeId)
    }

    override suspend fun deleteUserPlace(placeId: String) {
        placesDao.delete(placeId)
    }
}