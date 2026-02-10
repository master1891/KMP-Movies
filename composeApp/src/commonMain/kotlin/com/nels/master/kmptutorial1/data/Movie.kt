package com.nels.master.kmptutorial1.data

data class Movie(
    val id: Int,
    val title: String,
    val postr: String,
    val overview: String,
    val releaseDate: String,
    val backdrop: String?,
    val originalTitle: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double
)