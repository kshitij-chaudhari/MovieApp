package com.mintu.movieapp.di

import android.content.SharedPreferences
import com.mintu.data.api.MoviesAPIService
import com.mintu.data.datasource.local.LocalDataSourceImpl
import com.mintu.data.datasource.local.MovieDao
import com.mintu.data.datasource.remote.RemoteDataSourceImpl
import com.mintu.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(FragmentComponent::class)
class MoviesDataModule {
    @FragmentScoped
    @Provides
    fun provideRemoteDataSource(apiService: MoviesAPIService): RemoteDataSourceImpl {
        return RemoteDataSourceImpl(
            apiService
        )
    }

    @FragmentScoped
    @Provides
    fun provideLocalDataSource(movieDao: MovieDao): LocalDataSourceImpl {
        return LocalDataSourceImpl(
            movieDao
        )
    }

    @FragmentScoped
    @Provides
    fun provideMoviesRepositoryImpl(ioDispatcher: CoroutineDispatcher,
                              remoteDataSourceImpl: RemoteDataSourceImpl,
                              localDataSourceImpl: LocalDataSourceImpl,
                                    sharedPreferences: SharedPreferences
    ): MoviesRepositoryImpl {
        return MoviesRepositoryImpl(
            dispatcher = ioDispatcher,
            remoteDataSourceImpl = remoteDataSourceImpl,
            localDataSourceImpl = localDataSourceImpl,
            sharedPreferences = sharedPreferences
        )
    }
}