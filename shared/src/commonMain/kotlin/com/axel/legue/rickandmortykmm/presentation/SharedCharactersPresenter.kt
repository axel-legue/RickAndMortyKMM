package com.axel.legue.rickandmortykmm.presentation

import com.axel.legue.rickandmortykmm.data.network.models.CharactersParam
import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult
import com.axel.legue.rickandmortykmm.domain.models.Character
import com.axel.legue.rickandmortykmm.domain.repositories.CharactersRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedCharactersPresenter constructor(
    private val upcomingMoviesRepository: CharactersRepository
) : KoinComponent {

    private val viewModelScope = CoroutineScope(Dispatchers.Default)
    private val _characters = MutableStateFlow<List<Character>?>(emptyList())
    val characters: StateFlow<List<Character>?> = _characters

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            upcomingMoviesRepository.fetchChara(charaParam = CharactersParam()).collectLatest {
                when (it) {
                    is NetworkResult.Failure -> {
                        _error.value = it.errorMessage
                        Napier.e("SharedUpcomingMoviesPresenter error: ${it.errorMessage}")
                    }
                    is NetworkResult.Success -> {
                        _characters.value = it.data
                        Napier.e("SharedUpcomingMoviesPresenter Success: $it")
                    }
                }
            }
        }
    }
}