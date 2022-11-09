package com.axel.legue.rickandmortykmm.data.network

import com.axel.legue.rickandmortykmm.data.network.models.CharactersResponse
import com.axel.legue.rickandmortykmm.data.network.models.EpisodesResponse
import com.axel.legue.rickandmortykmm.domain.utils.BASE_URL_RICK
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiServiceImpl constructor(private val httpClient: HttpClient) : ApiService {

    companion object {
        const val END_POINT_CHARACTER = "character"
        const val END_POINT_EPISODE = "episode"
    }

    override suspend fun getCharacters(page: Int): CharactersResponse {
        return httpClient.get(urlString = BASE_URL_RICK.plus(END_POINT_CHARACTER)) {
            parameter("page", page)
        }.body()
    }

    override suspend fun getEpisodes(page: Int): EpisodesResponse {
        return httpClient.get(urlString = BASE_URL_RICK.plus(END_POINT_EPISODE)) {
            parameter("page", page)
        }.body()
    }
}
