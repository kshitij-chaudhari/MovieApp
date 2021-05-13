package com.mintu.domain.repository

import com.mintu.domain.entities.Data
import com.mintu.domain.extension.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMoviesList(query: String, currentTime: Long): Flow<Result<List<Data>>>
    fun lastFetchTime(currentTime: Long, lapseTime: Int): Boolean
}