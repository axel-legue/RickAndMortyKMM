package com.axel.legue.whatmovieskmm.data.network

import com.axel.legue.whatmovieskmm.domain.utils.START_PAGE_INDEX
import com.axel.legue.whatmovieskmm.data.network.models.UpcomingMoviesResponse

interface UpcomingMoviesService {
    suspend fun fetchUpComingMovies(page: Int = START_PAGE_INDEX): UpcomingMoviesResponse
}