package com.mintu.data.api

import com.mintu.data.model.Movies
import retrofit2.http.GET

interface MoviesAPIService {

    @GET("movies")
    suspend fun getMoviesList(): Movies
}