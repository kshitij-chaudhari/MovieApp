@file:Suppress("unused")

package com.mintu.movieapp.di

import com.mintu.data.repository.MoviesRepositoryImpl
import com.mintu.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MoviesAbstractModule {
    @Binds
    abstract fun bindRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}