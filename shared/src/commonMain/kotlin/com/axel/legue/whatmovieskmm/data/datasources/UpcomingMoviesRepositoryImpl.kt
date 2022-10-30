package com.axel.legue.whatmovieskmm.data.datasources

import com.axel.legue.whatmovieskmm.data.mappers.upcomingMovieResponseToMovies
import com.axel.legue.whatmovieskmm.data.network.UpcomingMoviesService
import com.axel.legue.whatmovieskmm.data.network.models.UpcomingMoviesParam
import com.axel.legue.whatmovieskmm.data.network.utils.NetworkResult
import com.axel.legue.whatmovieskmm.data.network.utils.safeApiCall
import com.axel.legue.whatmovieskmm.domain.models.Movie
import com.axel.legue.whatmovieskmm.domain.repositories.UpcomingMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UpcomingMoviesRepositoryImpl constructor(
    private val upcomingMoviesService: UpcomingMoviesService
) : UpcomingMoviesRepository {
    override suspend fun fetchUpcomingMovies(upcomingMoviesParam: UpcomingMoviesParam): Flow<NetworkResult<List<Movie>>> {
        val movies = safeApiCall {
            val upcomingMovies = upcomingMoviesService.fetchUpComingMovies(page = upcomingMoviesParam.page)
            upcomingMovies.upcomingMovieResponseToMovies()
        }
        return flowOf(movies)
    }
}
