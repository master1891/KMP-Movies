package com.nels.master.kmptutorial1.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nels.master.kmptutorial1.data.Movie
import com.nels.master.kmptutorial1.data.MoviesService
import com.nels.master.kmptutorial1.data.RemoteMovie
import com.nels.master.kmptutorial1.data.movies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesService: MoviesService
) : ViewModel() {

    var state by mutableStateOf(UIState())
        private set

    init {
        viewModelScope.launch {
            state = UIState(loading = true)
            delay(2000)
            state = UIState(
                loading = false,
                movies = moviesService.fetchMoviesPopularMovies().results.map { it.toDomainMovie() }
            )
        }
    }


    data class UIState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )

}

private fun RemoteMovie.toDomainMovie(): Movie {

    return Movie(
        id = id,
        title = title,
        postr = "https://image.tmdb.org/t/p/w500/$posterPath"
    )
}
