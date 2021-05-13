package com.mintu.data.datasource.local

import com.mintu.data.model.Data

interface LocalDataSource {
    suspend fun getMoviesListData(): List<Data>
    suspend fun getMoviesListByFilter(query: String): List<Data>
    suspend fun addMoviesList(movies: List<Data>)
}