package com.axel.legue.rickandmortykmm.data.mappers

import com.axel.legue.rickandmortykmm.data.network.models.CharactersResponse
import com.axel.legue.rickandmortykmm.data.network.models.EpisodesResponse
import com.axel.legue.rickandmortykmm.domain.models.Character
import com.axel.legue.rickandmortykmm.domain.models.Episode

fun CharactersResponse.toChara(): List<Character> = results.map { result ->
    with(result) {
        Character(name = name, species = species, image = image)
    }
}

fun EpisodesResponse.toEpisodes(): List<Episode> = results.map { result ->
    with(result) {
        Episode(
            id = id,
            name = name,
            airDate = air_date,
            episode = episode
        )
    }
}
