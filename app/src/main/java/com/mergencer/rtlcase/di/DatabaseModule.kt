package com.mergencer.rtlcase.di

import android.content.Context
import androidx.room.Room
import com.mergencer.rtlcase.data.AppDatabase
import com.mergencer.rtlcase.data.UserPlacesDao
import com.mergencer.rtlcase.data.UserWeatherByLocationDao
import com.mergencer.rtlcase.data.model.Converters
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserPlacesDao(database: AppDatabase): UserPlacesDao {
        return database.userPlacesDao()
    }

    @Provides
    @Singleton
    fun provideUserWeatherByLocationDao(database: AppDatabase): UserWeatherByLocationDao {
        return database.userWeatherByLocationDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context, moshi: Moshi): AppDatabase {
        val converters = Converters(moshi)

        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "rtl_case.db"
        )
            .addTypeConverter(converters)
            .fallbackToDestructiveMigration()
            .build()
    }

}