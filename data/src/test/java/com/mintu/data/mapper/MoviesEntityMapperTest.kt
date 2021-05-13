package com.mintu.data.mapper

import com.mintu.data.model.MockMoviesList
import com.mintu.data.model.Movies
import com.mintu.domain.entities.Movies as MoviesDomain
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesEntityMapperTest {

    @RelaxedMockK
    private lateinit var moviesEntityMapper: MoviesEntityMapper

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
        every { moviesEntityMapper.toDomain(movies) } returns moviesDomain

        val data = moviesEntityMapper.toDomain(movies)

        assertEquals(data, moviesDomain)
    }

    @Test
    fun toDomainListData() {
        every { moviesEntityMapper.toDomain(movies.data) } returns moviesDomain.data

        val data = moviesEntityMapper.toDomain(movies.data)

        assertEquals(data, moviesDomain.data)
    }

    @Test
    fun toDomainData() {
        every { moviesEntityMapper.toDomain(movies.data[0]) } returns moviesDomain.data[0]

        val data = moviesEntityMapper.toDomain(movies.data[0])

        assertEquals(data, moviesDomain.data[0])
    }
}