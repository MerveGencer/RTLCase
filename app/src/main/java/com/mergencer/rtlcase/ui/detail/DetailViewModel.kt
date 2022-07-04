package com.mergencer.rtlcase.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mergencer.rtlcase.data.WeathersRepository
import com.mergencer.rtlcase.data.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(
    private val repository: WeathersRepository): ViewModel() {

    private val _navigateBack = MutableLiveData<Boolean>()
    val navigateBack: LiveData<Boolean>
        get() = _navigateBack

    private val _showAlertDialogToDelete = MutableLiveData<WeatherReport>()
    val showAlertDialogToDelete: LiveData<WeatherReport>
        get() = _showAlertDialogToDelete

    fun onBackClick() {
        _navigateBack.value = true
    }

    fun doneNavigating() {
        _navigateBack.value = null
        _showAlertDialogToDelete.value = null
    }

    fun onDeleteClick(weatherReport: WeatherReport) {
        _showAlertDialogToDelete.value = weatherReport

    }

    fun deleteWeatherReport(weatherReport: WeatherReport) {
        weatherReport.placeId?.let {
            viewModelScope.launch {
                repository.deleteWeatherReport(it)
            }
            _navigateBack.value = true
        }
    }
}