@file:Suppress("unused")

package com.mintu.data.datasource.local

import com.mintu.domain.entities.Data as DataDomain
import com.mintu.domain.entities.Movies as MoviesDomain

class MockMoviesList {

    fun getMoviesDomain(): MoviesDomain {
        return MoviesDomain(data = listOf(
            DataDomain(
                id = 912312,
                genre = "Action",
                poster = "https://image.tmdb.org",
                title = "The Maze Runner",
                year = "2014"
            ),
            DataDomain(
                id = 912313,
                genre = "Comedy",
                poster = "https://image.tmdb.org",
                title = "The Iron Man",
                year = "2016"
            )
        ))
    }

}