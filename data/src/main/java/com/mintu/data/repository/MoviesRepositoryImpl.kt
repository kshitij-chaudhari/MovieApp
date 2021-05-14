package com.mintu.data.repository

import android.content.SharedPreferences
import com.mintu.data.datasource.local.LocalDataSourceImpl
import com.mintu.data.datasource.remote.RemoteDataSourceImpl
import com.mintu.data.mapper.MoviesEntityMapper
import com.mintu.domain.extension.repoFlow
import com.mintu.domain.repository.MoviesRepository
import com.mintu.domain.util.lastFetchTime
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

private const val LAPSE_TIME_DURATION_IN_SECONDS = 1 * 60

class MoviesRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl,
    private val sharedPreferences: SharedPreferences
): MoviesRepository{

    override suspend fun getMoviesList(query: String, currentTime: Long, isConnected: Boolean) = repoFlow {
        if(query.isBlank()) {
            if(lastFetchTime(currentTime, LAPSE_TIME_DURATION_IN_SECONDS, isConnected)) {
                sharedPreferences.lastFetchTime = currentTime
                val data = MoviesEntityMapper().toDomain(remoteDataSourceImpl.getMoviesListData())
                localDataSourceImpl.addMoviesList(MoviesEntityMapper().toData(data))
            }
            MoviesEntityMapper().toDomain(localDataSourceImpl.getMoviesListData())
        } else {
            MoviesEntityMapper().toDomain(localDataSourceImpl.getMoviesListByFilter(
                query.capitalize(Locale.UK)))
        }
    }.flowOn(dispatcher)

    override fun lastFetchTime(currentTime: Long, lapseTime: Int, isConnected: Boolean): Boolean {
        if(!isConnected) return false
        val lastTime  = sharedPreferences.lastFetchTime
        val diff = (currentTime - lastTime) / 1000
        return diff > lapseTime
    }
}