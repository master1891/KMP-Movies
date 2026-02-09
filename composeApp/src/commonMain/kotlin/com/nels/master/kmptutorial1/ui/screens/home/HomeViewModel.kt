package com.nels.master.kmptutorial1.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.kmptutorial1.Movie
import com.nels.master.kmptutorial1.movies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var state by mutableStateOf(UIState())
        private set

    init {
        viewModelScope.launch {
            state = UIState(loading = true)
            delay(2000)
            state = UIState(loading = false, movies = movies)
        }
    }


    data class UIState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )


}