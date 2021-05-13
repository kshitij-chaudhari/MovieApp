package com.mintu.movieapp.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.mintu.data.api.MoviesAPIService
import com.mintu.data.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideMoviesAPIService(): MoviesAPIService =
        RetrofitBuilder.getRetrofit().create(MoviesAPIService::class.java)

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}