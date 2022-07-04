package com.mergencer.rtlcase.di

import com.mergencer.rtlcase.data.WeathersRepositoryImpl
import com.mergencer.rtlcase.data.WeathersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindWeathersRepository(impl: WeathersRepositoryImpl): WeathersRepository






}