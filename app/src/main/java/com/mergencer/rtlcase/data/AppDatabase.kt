package com.mergencer.rtlcase.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mergencer.rtlcase.data.model.Converters
import com.mergencer.rtlcase.data.model.UserPlace
import com.mergencer.rtlcase.data.model.WeatherReport

@Database(entities = [UserPlace::class, WeatherReport::class], version = 8, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userPlacesDao(): UserPlacesDao
    abstract fun userWeatherByLocationDao(): UserWeatherByLocationDao
}
