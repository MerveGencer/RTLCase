package com.mergencer.rtlcase.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.mergencer.rtlcase.data.local.WeatherLocalDataSource
import com.mergencer.rtlcase.data.model.UserPlace
import com.mergencer.rtlcase.data.model.WeatherReport
import com.mergencer.rtlcase.data.remote.WeatherRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject


class WeathersRepositoryImpl @Inject constructor(
    private val localDataSource: WeatherLocalDataSource,
    private val remoteDataSource: WeatherRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): WeathersRepository {

    override val weatherReports: Flow<List<WeatherReport>> = localDataSource.getWeatherReports()

    @WorkerThread
    override suspend fun fetchUserPlaces() = withContext(ioDispatcher) {
        localDataSource.getUserPlaces()
    }

    @WorkerThread
    override suspend fun fetchWeatherReports() = withContext(ioDispatcher) {
        if (weatherReports.singleOrNull() == null) {
            val userPlaces = localDataSource.getUserPlaces()
            userPlaces?.forEach {
                val remoteResponse = remoteDataSource.fetchWeatherReportByLocation(it.lat, it.lng)
                remoteResponse?.let { rr ->
                    rr.placeId = it.id
                    rr.placeName = it.name
                    rr.placeAddress = it.address
                    rr.id = it.id
                    localDataSource.insertWeatherReport(rr) }
            }
        }
    }

    @WorkerThread
    override suspend fun insertUserPlace(userPlace: UserPlace) {
        val alreadyExists = localDataSource.getUserPlaceById(userPlace.id)
        if (alreadyExists != null) return
        localDataSource.insertUserPlace(userPlace)
        val remoteResponse = remoteDataSource.fetchWeatherReportByLocation(userPlace.lat, userPlace.lng)
        remoteResponse?.let {
            it.placeId = userPlace.id
            it.placeName = userPlace.name
            it.placeAddress = userPlace.address
            it.id = userPlace.id
            localDataSource.insertWeatherReport(it) }
    }

    @WorkerThread
    override suspend fun deleteWeatherReport(placeId: String) = withContext(ioDispatcher) {
        localDataSource.deleteWeatherReport(placeId)
        localDataSource.deleteUserPlace(placeId)
    }
}