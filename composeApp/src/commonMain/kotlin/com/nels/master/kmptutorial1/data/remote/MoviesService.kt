package com.nels.master.kmptutorial1.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MoviesService(
    private val client: HttpClient
) {
    suspend fun fetchMoviesPopularMovies(region: String): RemoteResult {
        return client
            .get("/3/discover/movie"){
                parameter("sort_by", "popularity.desc")
                parameter("region", region)
            }
            .body<RemoteResult>()
    }

    suspend fun fetchMovieById(idMovie: Int): RemoteMovie {
        return client
            .get("/3/movie/$idMovie")
            .body<RemoteMovie>()
    }

}