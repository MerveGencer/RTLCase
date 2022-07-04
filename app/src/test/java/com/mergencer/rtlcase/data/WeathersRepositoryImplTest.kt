package com.mergencer.rtlcase.data

import com.mergencer.rtlcase.data.model.UserPlace
import com.mergencer.rtlcase.data.model.WeatherPrimitive
import com.mergencer.rtlcase.data.model.WeatherReport
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.*
import org.hamcrest.core.IsEqual
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WeathersRepositoryImplTest {

    private val weatherReport0 = WeatherReport("0", "0", "Istanbul", "Istanbul/ Turkey", 1.0, 1.0,"timezone", WeatherPrimitive(
        1L,
        10.0,
        11.0,
        1,
        1,
        1.1,
        listOf()
    ), listOf(), null)
    private val weatherReport1 = WeatherReport("1", "1", "Amsterdam", "Amsterdam/ Netherlands", 1.1, 1.1,"timezone", WeatherPrimitive(1L,
        10.0,
        11.0,
        1,
        1,
        1.1,
        listOf()
    ), listOf(), null)

    private lateinit var remoteDataSource: FakeRemoteDataSource
    private lateinit var localDataSource: FakeLocalDataSource

    // Class under test
    private lateinit var repository: WeathersRepositoryImpl

    @BeforeEach
    fun setup() {
        remoteDataSource = FakeRemoteDataSource(mutableListOf(weatherReport0, weatherReport1))
        localDataSource = FakeLocalDataSource(mutableListOf(), mutableListOf())

        // Get a reference to the class under test
        repository = WeathersRepositoryImpl(
            localDataSource, remoteDataSource
        )
    }

    @Test
    fun fetchUserPlacesTest()  = runBlocking  {
        val userPlacesRepo = repository.fetchUserPlaces()
        MatcherAssert.assertThat(userPlacesRepo, IsEqual(listOf()))
    }

    @Test
    fun insertUserPlace() = runBlocking  {
        val newUserPlace = UserPlace("0", 1.0, 1.0,"Istanbul", "Istanbul/ Turkey")
        repository.insertUserPlace(newUserPlace)
        MatcherAssert.assertThat(repository.fetchUserPlaces(), hasItem(newUserPlace))
    }

    @Test
    fun deleteWeatherReport() = runBlocking  {
        val newUserPlace = UserPlace("0", 1.0, 1.0,"Istanbul", "Istanbul/ Turkey")
        repository.insertUserPlace(newUserPlace)
        MatcherAssert.assertThat(repository.fetchUserPlaces(), hasItem(newUserPlace))

        repository.deleteWeatherReport("0")
        MatcherAssert.assertThat(repository.fetchUserPlaces(), not(hasItem(newUserPlace)))
    }


}