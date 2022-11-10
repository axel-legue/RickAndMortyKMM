package com.axel.legue.rickandmortykmm.presentation

import com.axel.legue.rickandmortykmm.data.network.models.RequestParam
import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult
import com.axel.legue.rickandmortykmm.domain.models.Episode
import com.axel.legue.rickandmortykmm.domain.repositories.EpisodesRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedEpisodesPresenter constructor(
    private val episodeRepository: EpisodesRepository
) : KoinComponent {

    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val _episodes = MutableStateFlow<List<Episode>?>(emptyList())
    val episodes: StateFlow<List<Episode>?> = _episodes

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchEpisodes()
    }

    private fun fetchEpisodes() {
        viewModelScope.launch {
            episodeRepository.fetchEpisodes(param = RequestParam()).collectLatest {
                when (it) {
                    is NetworkResult.Failure -> {
                        _error.value = it.errorMessage
                        Napier.e("SharedEpisodesPresenter error: ${it.errorMessage}")
                    }
                    is NetworkResult.Success -> {
                        _episodes.value = it.data
                        Napier.e("SharedEpisodesPresenter Success: $it")
                    }
                }
            }
        }
    }
}