package com.mintu.domain.usecase

import com.mintu.domain.entities.Data
import com.mintu.domain.extension.Result
import com.mintu.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(query: String = "", currentTime: Long): Flow<Result<List<Data>>> {
        return moviesRepository.getMoviesList(query, currentTime)
    }
}