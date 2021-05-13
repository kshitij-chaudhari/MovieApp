package com.mintu.data.datasource.remote

import com.mintu.data.api.MoviesAPIService
import com.mintu.data.model.Data
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val moviesAPIService: MoviesAPIService
): RemoteDataSource {
    override suspend fun getMoviesListData(): List<Data> = moviesAPIService.getMoviesList().data
}