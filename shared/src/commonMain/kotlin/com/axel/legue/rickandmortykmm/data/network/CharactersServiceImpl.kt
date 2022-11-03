package com.axel.legue.rickandmortykmm.data.network

import com.axel.legue.rickandmortykmm.data.network.models.CharactersResponse
import com.axel.legue.rickandmortykmm.domain.utils.BASE_URL_RICK
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CharactersServiceImpl constructor(private val httpClient: HttpClient) : CharactersServices {

    companion object {
        const val END_POINT = "character"
    }
    override suspend fun getCharacters(page: Int): CharactersResponse {
        return httpClient.get(urlString = BASE_URL_RICK.plus(END_POINT)) {
            parameter("page", page)
        }.body()
    }
}
