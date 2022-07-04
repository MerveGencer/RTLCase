package com.mergencer.rtlcase.ui

import androidx.lifecycle.*
import com.google.android.libraries.places.api.model.Place
import com.mergencer.rtlcase.data.WeathersRepository
import com.mergencer.rtlcase.data.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val repository: WeathersRepository): ViewModel() {

    private val _status = MutableLiveData<ApiCallStatus>()
    val status: LiveData<ApiCallStatus>
        get() = _status

    val weathersInUserPlaces: LiveData<List<WeatherReport>> = repository.weatherReports.asLiveData()

    init {
        fetchWeatherReports()
    }

    private fun fetchWeatherReports() {
        viewModelScope.launch {
            try {
                _status.value = ApiCallStatus.LOADING
                repository.fetchWeatherReports()
                _status.value = ApiCallStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiCallStatus.ERROR
                Timber.e(e)
            }
        }
    }

    fun onUserPlaceSelected(place: Place) {
        viewModelScope.launch {
            place.id?.let {
                repository.insertUserPlace(UserPlace(it, place.latLng.latitude, place.latLng.longitude, place.name, place.address))
            }
        }
    }

    fun removeItem(weatherReport: WeatherReport) {
        weatherReport.placeId?.let {
            viewModelScope.launch {
                repository.deleteWeatherReport(it)
            }
        }
    }
}