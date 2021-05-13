package com.mintu.domain.usecase

import com.google.common.truth.Truth
import com.mintu.domain.entities.MockMoviesList
import com.mintu.domain.entities.Movies
import com.mintu.domain.extension.Result
import com.mintu.domain.repository.MoviesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {
    @RelaxedMockK
    lateinit var repo: MoviesRepository

    private lateinit var useCase: GetMoviesUseCase
    private lateinit var movies: Movies

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetMoviesUseCase(repo)
        movies = MockMoviesList().getMovies()
    }

    @Test
    fun `flow is success result movies`() {
        runBlocking {
            coEvery { repo.getMoviesList("", 0) } returns flow { emit(Result.Success(movies.data)) }

            val data =  useCase.invoke("", 0)

            coVerify { useCase.invoke("", 0) }
            data.collect {
                Truth.assertThat(it is Result.Success).isTrue()
            }
        }
    }

    @Test
    fun `flow is loading result movies`() {
        runBlocking {
            coEvery { repo.getMoviesList("", 0) } returns flow { emit(Result.Loading) }

            val data =  useCase.invoke("", 0)

            coVerify { useCase.invoke("", 0) }
            data.collect { result ->
                Truth.assertThat(result is Result.Loading).isTrue()
            }
        }
    }

    @Test
    fun `flow is error result movies`() {
        runBlocking {
            coEvery { repo.getMoviesList("", 0) } returns flow { emit(Result.Error("Error")) }

            val data =  useCase.invoke("", 0)

            coVerify { useCase.invoke("", 0) }
            data.collect { result ->
                Truth.assertThat(result is Result.Error).isTrue()
            }
        }
    }
}