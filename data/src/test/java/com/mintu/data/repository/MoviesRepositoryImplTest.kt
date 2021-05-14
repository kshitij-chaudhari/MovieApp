package com.mintu.data.repository

import android.content.SharedPreferences
import com.google.common.truth.Truth
import com.mintu.data.datasource.local.LocalDataSourceImpl
import com.mintu.data.model.Movies as MoviesData
import com.mintu.data.datasource.remote.RemoteDataSourceImpl
import com.mintu.data.model.MockMoviesList
import com.mintu.domain.extension.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {
    private val coroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var repositoryImpl: MoviesRepositoryImpl

    @RelaxedMockK
    private lateinit var remoteDataSource: RemoteDataSourceImpl

    @RelaxedMockK
    private lateinit var localeDataSource: LocalDataSourceImpl

    @RelaxedMockK
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var movies: MoviesData

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl = MoviesRepositoryImpl(coroutineDispatcher, remoteDataSource, localeDataSource, sharedPreferences)
        movies = MockMoviesList().getMovies()
    }

    @Test
    fun testGetMoviesList() {
        coroutineDispatcher.runBlockingTest {
            coEvery { remoteDataSource.getMoviesListData() } returns movies.data

            val repo = repositoryImpl.getMoviesList("", 0, false)

            repo.collect {
                if(it is Result.Success) {
                    Truth.assertThat(it.data).isEqualTo(MockMoviesList().getMoviesDomain().data)
                }
            }
        }
    }
}