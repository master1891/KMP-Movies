package com.nels.master.kmptutorial1.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.kmptutorial1.data.Movie
import com.nels.master.kmptutorial1.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviewRepository: MoviesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<UIState>(UIState())
    val uiState = _uiState.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _uiState.value = UIState(loading = true)
            moviewRepository.movies.collect {
                if (it.isNotEmpty())
                    _uiState.value = UIState(loading = false, movies = it)
            }
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )

}

