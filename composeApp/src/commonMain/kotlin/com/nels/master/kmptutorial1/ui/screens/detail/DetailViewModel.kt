package com.nels.master.kmptutorial1.ui.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.kmptutorial1.data.Movie
import com.nels.master.kmptutorial1.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val idMovie: Int,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    
    fun toggleFavorite() {
        uiState.value.movie?.let {movie ->
            viewModelScope.launch {
                moviesRepository.toggleFavorite(movie)
            }
        }
    }

    init {
        viewModelScope.launch {
            _uiState.value = UIState(loading = true)

            moviesRepository.fetchMovieById(idMovie).collect {
                _uiState.value = UIState(loading = false, movie = it)
            }

        }
    }

    data class UIState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )
}