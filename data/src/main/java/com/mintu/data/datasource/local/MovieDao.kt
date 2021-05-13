package com.mintu.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mintu.domain.entities.Data

@Dao
interface  MovieDao {
    @Query("SELECT * FROM movies ORDER BY year DESC")
    suspend fun getAllMovies() : List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMovies(movies: List<Data>)

    @Query("SELECT * FROM movies WHERE genre LIKE '%' || :genreTitleStr || '%' OR title LIKE '%' || :genreTitleStr || '%'")
    suspend fun getAllMoviesByGenreOrTitle(genreTitleStr: String = "") : List<Data>
}