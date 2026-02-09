package com.nels.master.kmptutorial1.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MoviesService(
    private val apiKey: String,
    private val client: HttpClient
) {
    suspend fun fetchMoviesPopularMovies(): RemoteResult {
        return client
            .get("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=efdb7075831d13ad101cc281333f2a03")
            .body<RemoteResult>()
    }

}