package com.mergencer.rtlcase.data

import androidx.room.*
import com.mergencer.rtlcase.data.model.WeatherReport
import kotlinx.coroutines.flow.Flow

@Dao
interface UserWeatherByLocationDao {

    /**
     * Returns all weather data stored in the db
     */
    @Query("SELECT * FROM weathers")
    fun getWeatherReports(): Flow<List<WeatherReport>>

    @Query("SELECT * FROM weathers WHERE lat == :lat AND lon == :lng")
    fun getWeatherReportByPlace(lat: Double, lng: Double): WeatherReport

    /**
     * Inserts new weather data to the table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherResponse: WeatherReport)


    /**
     * Clears weather data of a place
     */
    @Query("DELETE from weathers WHERE placeId == :placeId")
    fun delete(placeId: String)

}
