package com.axel.legue.whatmovieskmm.data.network

import com.axel.legue.whatmovieskmm.data.network.models.UpcomingMoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class UpcomingMoviesServiceImpl constructor(private val httpClient: HttpClient) : UpcomingMoviesService {

    companion object {
        const val END_POINT = "movie/upcoming"
        const val PAGE = "page"
    }

    override suspend fun fetchUpComingMovies(page: Int): UpcomingMoviesResponse {
        return httpClient.get(urlString = END_POINT) {
            url {
                parameters.append(PAGE, page.toString())
            }
        }.body()
    }
}
