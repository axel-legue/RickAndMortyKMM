package com.axel.legue.whatmovieskmm.domain.repositories

import com.axel.legue.whatmovieskmm.domain.models.Movie
import com.axel.legue.whatmovieskmm.data.network.models.UpcomingMoviesParam
import com.axel.legue.whatmovieskmm.data.network.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UpcomingMoviesRepository {
    suspend fun fetchUpcomingMovies(upcomingMoviesParam: UpcomingMoviesParam): Flow<NetworkResult<List<Movie>>>
}
