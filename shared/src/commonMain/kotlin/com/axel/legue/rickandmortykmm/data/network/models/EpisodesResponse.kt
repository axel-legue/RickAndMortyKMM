package com.axel.legue.rickandmortykmm.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class EpisodesResponse(
    val info: Info,
    val results: List<Result>
) {
    @Serializable
    data class Info(
        val count: Int,
        val next: String,
        val pages: Int,
        val prev: Int?
    )

    @Serializable
    data class Result(
        val air_date: String,
        val characters: List<String>,
        val created: String,
        val episode: String,
        val id: Int,
        val name: String,
        val url: String
    )
}