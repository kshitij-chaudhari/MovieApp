package com.mintu.data.datasource.remote

import com.mintu.data.api.MoviesAPIService
import com.mintu.data.model.Movies
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    @RelaxedMockK
    private lateinit var apiService: MoviesAPIService

    @RelaxedMockK
    private lateinit var moviesData: Movies

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSourceImpl = RemoteDataSourceImpl(apiService)
    }

    @Test
    fun getMoviesListDataSource() {
        runBlockingTest {
            coEvery { remoteDataSourceImpl.getMoviesListData() } returns moviesData.data

            val data = remoteDataSourceImpl.getMoviesListData()

            assertEquals(data, moviesData.data)
        }
    }
}