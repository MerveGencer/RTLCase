package com.mergencer.rtlcase.di

import com.mergencer.rtlcase.data.UserPlacesDao
import com.mergencer.rtlcase.data.UserWeatherByLocationDao
import com.mergencer.rtlcase.data.WeathersRepositoryImpl
import com.mergencer.rtlcase.data.WeathersRepository
import com.mergencer.rtlcase.data.local.impl.WeatherLocalDataSourceImpl
import com.mergencer.rtlcase.data.local.WeatherLocalDataSource
import com.mergencer.rtlcase.data.remote.WeatherRemoteDataSource
import com.mergencer.rtlcase.data.remote.impl.WeatherRemoteDataSourceImpl
import com.mergencer.rtlcase.network.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: WeatherRemoteDataSource,
                          localDataSource: WeatherLocalDataSource
    ): WeathersRepository {
        return WeathersRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: WeatherApiService): WeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(userPlacesDao: UserPlacesDao, weatherByLocationDao: UserWeatherByLocationDao): WeatherLocalDataSource {
        return WeatherLocalDataSourceImpl(userPlacesDao, weatherByLocationDao)
    }
}