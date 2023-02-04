package com.example.chucknorristest.di

import com.example.chucknorristest.rest.JokeAPI
import com.example.chucknorristest.viewModel.utils.BASE_URL
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

        @Provides
        fun providesJokeServiceApi(
            okHttpClient: OkHttpClient,
            moshi: Moshi
        ): JokeAPI =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create(JokeAPI::class.java)

        @Provides
        fun providesOkHttp(
            httpLoggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .callTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        @Provides
        fun providesMoshi(): Moshi =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        @Provides
        fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        @Provides
        fun providesIODispatcher(): CoroutineDispatcher =
            Dispatchers.IO

        @Provides
        fun providesCoroutineScope(ioDispatcher: CoroutineDispatcher): CoroutineScope =
            CoroutineScope(ioDispatcher)
}