package com.example.nasapictures.hilt

import android.app.Application
import android.content.Context
import com.example.nasapictures.home.NasaAllDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    fun provideContext(application:Application):Context = application.applicationContext

    fun provideDetailRepository(context: Context) : NasaAllDataRepository = NasaAllDataRepository(context)
}