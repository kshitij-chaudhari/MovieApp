package com.mintu.data.datasource.remote

import com.mintu.data.model.Data

interface RemoteDataSource {
    suspend fun getMoviesListData(): List<Data>
}