package com.nels.master.kmptutorial1

data class Movie(
    val id: Int,
    val title: String,
    val postr: String
)


val movies = (1..100).map {
    Movie(
        id = it,
        title = "Movie $it",
        postr = "https://picsum.photos/200/300?id=$it"
    )
}