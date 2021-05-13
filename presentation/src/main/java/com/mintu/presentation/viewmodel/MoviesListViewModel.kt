package com.mintu.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintu.domain.extension.Result
import com.mintu.domain.usecase.GetMoviesUseCase
import com.mintu.presentation.mapper.MoviesUIMapper
import com.mintu.presentation.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesUseCase: GetMoviesUseCase
): ViewModel() {

    private val _moviesListState : MutableStateFlow<List<Data>> = MutableStateFlow(emptyList())
    val moviesListState: StateFlow<List<Data>> = _moviesListState

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> = _errorState

    fun getMoviesList(query: String = "", currentTime: Long = 0) = viewModelScope.launch {
        moviesUseCase.invoke(query, currentTime).collect {
            when(it) {
                is Result.Loading -> {
                    _loadingState.value = true
                }
                is Result.Success -> {
                    _loadingState.value = false
                    _moviesListState.value = MoviesUIMapper().toUI(it.data)
                }
                is Result.Error -> {
                    _errorState.value = it.error
                    _loadingState.value = false
                }
            }
        }
    }
}