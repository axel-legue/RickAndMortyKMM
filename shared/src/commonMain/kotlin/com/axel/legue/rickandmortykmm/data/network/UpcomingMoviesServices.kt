package com.axel.legue.rickandmortykmm.data.network

import com.axel.legue.rickandmortykmm.data.network.models.CharactersResponse
import com.axel.legue.rickandmortykmm.domain.utils.START_PAGE_INDEX

interface CharactersServices {
    suspend fun getCharacters(page: Int = START_PAGE_INDEX): CharactersResponse
}