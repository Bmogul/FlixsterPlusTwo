package com.example.flixsterplustwo

data class MovieModel(
    val id: Int?,
    val poster_path: String?,
    val title: String?,
    val overview: String?,
    val vote_average: Float?,
    val genres: List<genre>?,
)

data class genre(
    val id: Int?,
    val name: String?
)