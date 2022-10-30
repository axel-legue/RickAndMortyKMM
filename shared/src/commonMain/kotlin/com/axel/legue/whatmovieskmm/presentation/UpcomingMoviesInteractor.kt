package com.axel.legue.whatmovieskmm.presentation

import com.axel.legue.whatmovieskmm.data.network.models.UpcomingMoviesParam
import com.axel.legue.whatmovieskmm.data.network.utils.NetworkResult
import com.axel.legue.whatmovieskmm.domain.models.Movie
import com.axel.legue.whatmovieskmm.domain.repositories.UpcomingMoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class UpcomingMoviesInteractor constructor(
    private val upcomingMoviesRepository: UpcomingMoviesRepository
) : KoinComponent {

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val _upcomingMovies = MutableStateFlow<List<Movie>?>(emptyList())
    val upcomingMovies: StateFlow<List<Movie>?> = _upcomingMovies

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchUpComingMovies() {
        viewModelScope.launch {
            upcomingMoviesRepository.fetchUpcomingMovies(upcomingMoviesParam = UpcomingMoviesParam()).collectLatest {
                when (it) {
                    is NetworkResult.Failure -> _error.value = it.errorMessage
                    is NetworkResult.Success -> _upcomingMovies.value = it.data
                }
            }
        }
    }
}