package com.mintu.data.model

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DataTest {

    private lateinit var movieData: Data

    @Before
    fun setUp() {
        movieData = MockMoviesList().getData()
    }

    @Test
    fun getGenre() {
        val data = movieData.copy(genre = "Action")
        assertEquals(movieData.genre, data.genre)
    }

    @Test
    fun getId() {
        val data = movieData.copy(id = 912312)
        assertEquals(movieData.id, data.id)
    }

    @Test
    fun getPoster() {
        val data = movieData.copy(poster = "https://image.tmdb.org")
        assertEquals(movieData.poster, data.poster)
    }

    @Test
    fun getTitle() {
        val data = movieData.copy(title = "The Maze Runner")
        assertEquals(movieData.title, data.title)
    }

    @Test
    fun getYear() {
        val data = movieData.copy(year = "2014")
        assertEquals(movieData.year, data.year)
    }
}