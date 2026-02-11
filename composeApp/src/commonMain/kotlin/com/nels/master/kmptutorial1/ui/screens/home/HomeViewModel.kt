package com.nels.master.kmptutorial1.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.kmptutorial1.data.Movie
import com.nels.master.kmptutorial1.data.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviewRepository: MoviesRepository
) : ViewModel() {

    var state by mutableStateOf(UIState())
        private set


    fun onUiReady() {
        viewModelScope.launch {
            state = UIState(loading = true)
            moviewRepository.movies.collect {
                if (it.isNotEmpty())
                    state = UIState(loading = false, movies = it)
            }
        }
    }

    data class UIState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )

}

