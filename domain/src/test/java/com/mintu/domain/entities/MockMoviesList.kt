package com.mintu.domain.entities

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