package com.nels.master.kmptutorial1.ui.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.kmptutorial1.data.Movie
import com.nels.master.kmptutorial1.data.MoviesRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val idMovie: Int,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var uiState = mutableStateOf(UIState())
        private set

    init {
        viewModelScope.launch {
            uiState.value = UIState(loading = true)

            moviesRepository.fetchMovieById(idMovie).collect {
                uiState.value = UIState(loading = false, movie = it)
            }

        }
    }

    data class UIState(
        val loading: Boolean = false,
        val movie: Movie? = null
    )
}