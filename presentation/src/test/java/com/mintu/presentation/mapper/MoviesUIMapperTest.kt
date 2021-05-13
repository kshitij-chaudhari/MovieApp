package com.mintu.presentation.mapper

import com.mintu.presentation.model.MockMoviesList
import com.mintu.domain.entities.Movies as MoviesDomain
import com.mintu.presentation.model.Movies
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesUIMapperTest {
    @RelaxedMockK
    private lateinit var moviesUIMapper: MoviesUIMapper

    private lateinit var moviesDomain: MoviesDomain

    private lateinit var movies: Movies

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesDomain = MockMoviesList().getMoviesDomain()
        movies = MockMoviesList().getMovies()
    }

    @Test
    fun toDomainMovies() {
        every { moviesUIMapper.toUI(moviesDomain) } returns movies

        val data = moviesUIMapper.toUI(moviesDomain)

        assertEquals(data, movies)
    }

    @Test
    fun toDomainListData() {
        every { moviesUIMapper.toUI(moviesDomain.data) } returns movies.data

        val data = moviesUIMapper.toUI(moviesDomain.data)

        assertEquals(data, movies.data)
    }

    @Test
    fun toDomainData() {
        every { moviesUIMapper.toUI(moviesDomain.data[0]) } returns movies.data[0]

        val data = moviesUIMapper.toUI(moviesDomain.data[0])

        assertEquals(data, movies.data[0])
    }
}