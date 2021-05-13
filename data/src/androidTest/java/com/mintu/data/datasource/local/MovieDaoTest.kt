package com.mintu.data.datasource.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.getMovieDao()
    }

    @Test
    fun testAddAllMovies() = runBlocking {
        val dataList = MockMoviesList().getMoviesDomain().data
        dao.addAllMovies(dataList)

        val allMoviesList  = dao.getAllMovies()
        Truth.assertThat(allMoviesList[0]).isEqualTo(dataList[1])
    }

    @Test
    fun testGetAllMovies() = runBlocking {
        val dataList = MockMoviesList().getMoviesDomain().data
        dao.addAllMovies(dataList)

        val allMoviesList  = dao.getAllMovies()
        Truth.assertThat(allMoviesList.size).isEqualTo(dataList.size)
    }

    @Test
    fun testGetAllMoviesByGenreOrTitle() = runBlocking {
        val dataList = MockMoviesList().getMoviesDomain().data
        dao.addAllMovies(dataList)

        val movie  = dao.getAllMoviesByGenreOrTitle("Action")
        Truth.assertThat(movie[0]).isEqualTo(dataList[0])
    }

    @After
    fun teardown() {
        database.close()
    }
}