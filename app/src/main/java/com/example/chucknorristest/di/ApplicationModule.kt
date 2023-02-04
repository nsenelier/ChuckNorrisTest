package com.example.chucknorristest.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context =
        application.applicationContext
}