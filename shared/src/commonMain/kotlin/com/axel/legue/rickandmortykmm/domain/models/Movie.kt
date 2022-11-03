package com.axel.legue.rickandmortykmm.domain.models

data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class Character(
    val name: String,
    val species: String,
    val image: String
)