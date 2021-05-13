package com.mintu.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.mintu.domain.entities.Movies
import com.mintu.presentation.model.Movies as MoviesUI
import com.mintu.domain.extension.Result
import com.mintu.domain.usecase.GetMoviesUseCase
import com.mintu.presentation.mapper.MoviesUIMapper
import com.mintu.presentation.model.MockMoviesList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class MoviesListViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: MoviesListViewModel


    private lateinit var movies: Movies

    @RelaxedMockK
    private lateinit var getUseCase: GetMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MoviesListViewModel(getUseCase)
        movies = MockMoviesList().getMoviesDomain()
    }

    @Test
    fun loadingState() {
        testCoroutineRule.runBlockingTest {
            coEvery { getUseCase.invoke("", 0) } returns flow { emit(Result.Loading) }

            viewModel.getMoviesList()

            Truth.assertThat(viewModel.loadingState.value).isEqualTo(true)
            assertEquals(viewModel.moviesListState.value, MoviesUI(emptyList()).data)
        }
    }

    @Test
    fun successState() {
        testCoroutineRule.runBlockingTest {
            coEvery { getUseCase.invoke("", 0) } returns flow {
                emit(Result.Success(movies.data))
            }
            viewModel.getMoviesList()

            assertEquals(viewModel.moviesListState.value, MoviesUIMapper().toUI(movies).data)
            Truth.assertThat(viewModel.loadingState.value).isEqualTo(false)
        }
    }

    @Test
    fun errorState() {
        testCoroutineRule.runBlockingTest {
            coEvery { getUseCase.invoke("", 0) } returns flow { emit(Result.Error("Error")) }

            viewModel.getMoviesList()

            Truth.assertThat(viewModel.loadingState.value).isEqualTo(false)
            assertEquals(viewModel.errorState.value, "Error")
        }
    }
}