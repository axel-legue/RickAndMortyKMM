package com.axel.legue.rickandmortykmm.domain.models

import kotlinx.datetime.LocalDate

data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
)
