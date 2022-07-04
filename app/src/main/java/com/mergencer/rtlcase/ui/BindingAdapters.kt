package com.mergencer.rtlcase.ui

import android.content.res.Configuration
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mergencer.rtlcase.R
import com.mergencer.rtlcase.data.model.Temperature
import com.mergencer.rtlcase.data.model.Weather
import com.mergencer.rtlcase.data.model.WeatherDescription
import com.mergencer.rtlcase.data.model.WeatherPrimitive
import java.text.SimpleDateFormat
import java.util.*

const val IMAGE_SERVER: String = " http://openweathermap.org/img/wn/10d@2x.png"

@BindingAdapter("dateTime")
fun TextView.setDateTime(dateTime: Long?) {
    dateTime?.let {
        val sdf = SimpleDateFormat("E hh:mm", Locale.ENGLISH)
        text = sdf.format(Date(dateTime*1000))
    }
}

@BindingAdapter("briefDescription")
fun TextView.setBriefDescription(weather: WeatherPrimitive?) {
    weather?.let {
        text = it.weatherDescription[0].main
    }
}

@BindingAdapter("weatherIcon")
fun setWeatherIcon(imgView: AppCompatImageView, description: List<WeatherDescription>?) {
    description?.let {
        val icon = it[0].icon
        Glide.with(imgView.context)
            .load("https://openweathermap.org/img/wn/$icon@2x.png")
            .fitCenter()
            .into(imgView)
    }
}

@BindingAdapter("temperature")
fun TextView.setTemperature(temperature: Temperature?) {
    temperature?.let {
        val dayOrNight = when (resources.configuration.uiMode) {
            Configuration.UI_MODE_NIGHT_NO -> temperature.day
            Configuration.UI_MODE_NIGHT_YES -> temperature.night
            else -> temperature.day
        }
        text = String.format(resources.getString(R.string.format_temperature), dayOrNight)
    }
}

@BindingAdapter("onLongClick")
fun setOnLongClickListener(view: View, block : () -> Unit) {
    view.setOnLongClickListener {
        block()
        return@setOnLongClickListener true
    }
}

@BindingAdapter("dayOfWeek")
fun TextView.setDayOfWeek(currentTime: Long) {
    val sdf = SimpleDateFormat("E", Locale.ENGLISH)
    text = sdf.format(Date(currentTime*1000))
}

@BindingAdapter("dayOfMonth")
fun TextView.setDayOfMonth(currentTime: Long) {
    val sdf = SimpleDateFormat("d MMM", Locale.ENGLISH)
    text = sdf.format(Date(currentTime*1000))
}


