package com.example.chucknorristest.di

import com.example.chucknorristest.rest.JokeRepository
import com.example.chucknorristest.rest.JokeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesJokeRepository(
        jokeRepositoryImpl: JokeRepositoryImpl
    ): JokeRepository
}