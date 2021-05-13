package com.mintu.data.datasource.local

import com.mintu.data.mapper.MoviesEntityMapper
import com.mintu.data.model.Data
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
): LocalDataSource {
    override suspend fun getMoviesListData(): List<Data> =
        MoviesEntityMapper().toData(movieDao.getAllMovies())

    override suspend fun getMoviesListByFilter(query: String) =
        MoviesEntityMapper().toData(movieDao.getAllMoviesByGenreOrTitle(genreTitleStr = query))

    override suspend fun addMoviesList(movies: List<Data>) {
        movieDao.addAllMovies(MoviesEntityMapper().toDomain(movies))
    }

}