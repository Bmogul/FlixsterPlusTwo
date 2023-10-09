package com.example.flixsterplustwo

import java.io.Serializable

data class MovieModel(
    val id: Int?,
    var poster_path: String?,
    val title: String?,
    val overview: String?,
    val tagline: String?,
    val vote_average: Float?,
    val genres: List<genre>?,
) : Serializable

data class genre(
    val id: Int?,
    val name: String?
) : Serializable