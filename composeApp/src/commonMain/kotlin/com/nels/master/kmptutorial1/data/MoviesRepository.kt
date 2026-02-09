package com.nels.master.kmptutorial1.data

class MoviesRepository(
    private val moviesService: MoviesService

) {
    suspend fun fetchMoviesPopularMovies(): List<Movie> {
        return moviesService.fetchMoviesPopularMovies().results.map { it.toDomainMovie() }
    }

    private fun RemoteMovie.toDomainMovie(): Movie {

        return Movie(
            id = id,
            title = title,
            postr = "https://image.tmdb.org/t/p/w500/$posterPath"
        )
    }

    suspend fun fetchMovieDetail(idMovie: Int): Movie {
        return moviesService.fetchMovieById(idMovie).toDomainMovie()
    }

}