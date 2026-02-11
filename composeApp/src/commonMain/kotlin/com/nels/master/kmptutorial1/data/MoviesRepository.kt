package com.nels.master.kmptutorial1.data

import com.nels.master.kmptutorial1.data.database.MoviesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class MoviesRepository(
    private val moviesService: MoviesService,
    private val moviesDao: MoviesDao

) {


     val movies: Flow<List<Movie>> = moviesDao.fetchPopularMovies().onEach {
        if (it.isEmpty()) {
            val moviesFromApi = moviesService.fetchMoviesPopularMovies().results.map { it.toDomainMovie() }
            moviesDao.insertMovies(moviesFromApi)
        }
    }



     fun fetchMovieById (idMovie: Int): Flow<Movie> = moviesDao.fetchMovieById(idMovie).onEach {
        if (it == null) {
            val movieFromApi = moviesService.fetchMovieById(idMovie).toDomainMovie()
            moviesDao.insertMovies(listOf(movieFromApi))
        }
    }


    private fun RemoteMovie.toDomainMovie(): Movie {

        return Movie(
            id = id,
            title = title,
            postr = "https://image.tmdb.org/t/p/w185/$posterPath",
            overview = overview,
            releaseDate = releaseDate,
            backdrop = backdropPath?.let { "https://image.tmdb.org/t/p/w780/$posterPath"},
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            popularity = popularity,
            voteAverage = voteAverage,
            isFavorite = false
        )

    }

    suspend fun fetchMovieDetail(idMovie: Int): Movie {
        return moviesService.fetchMovieById(idMovie).toDomainMovie()
    }

    suspend fun toggleFavorite(movie: Movie) {
        moviesDao.insertMovies(listOf(movie.copy(isFavorite = !movie.isFavorite)))
    }

}