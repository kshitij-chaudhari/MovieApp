package com.mintu.presentation.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MoviesTest {

    private lateinit var movies: Movies

    @Before
    fun setUp() {
        movies = MockMoviesList().getMovies()
    }

    @Test
    fun testGetData() {
        val moviesList = movies.copy(data = listOf(
            Data(
                id = 912312,
                genre = "Action",
                poster = "https://image.tmdb.org",
                title = "The Maze Runner",
                year = "2014"
            )
        ))
        assertEquals(movies.data, moviesList.data)
    }
}