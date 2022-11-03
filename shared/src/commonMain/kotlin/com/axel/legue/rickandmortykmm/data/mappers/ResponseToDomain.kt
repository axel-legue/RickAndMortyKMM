package com.axel.legue.rickandmortykmm.data.mappers

import com.axel.legue.rickandmortykmm.data.network.models.CharactersResponse
import com.axel.legue.rickandmortykmm.domain.models.Character

fun CharactersResponse.toChara(): List<Character> = results.map { result ->
    with(result) {
        Character(name = name, species = species, image = image)
    }
}
