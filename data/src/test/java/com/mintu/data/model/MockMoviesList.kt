package com.mintu.data.model

import com.mintu.domain.entities.Movies as MoviesDomain
import com.mintu.domain.entities.Data as DataDomain

class MockMoviesList {
    fun getMovies(): Movies {
        return Movies(data = listOf(
            Data(
                id = 912312,
                genre = "Action",
                poster = "https://image.tmdb.org",
                title = "The Maze Runner",
                year = "2014"
            )
        ))
    }

    fun getMoviesDomain(): MoviesDomain {
        return MoviesDomain(data = listOf(
            DataDomain(
                id = 912312,
                genre = "Action",
                poster = "https://image.tmdb.org",
                title = "The Maze Runner",
                year = "2014"
            )
        ))
    }

    fun getData(): Data {
        return Data(
            id = 912312,
            genre = "Action",
            poster = "https://image.tmdb.org",
            title = "The Maze Runner",
            year = "2014"
        )
    }

}