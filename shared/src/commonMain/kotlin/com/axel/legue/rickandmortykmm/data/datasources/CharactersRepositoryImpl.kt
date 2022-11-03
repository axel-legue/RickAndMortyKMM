package com.axel.legue.rickandmortykmm.data.datasources

import com.axel.legue.rickandmortykmm.data.mappers.toChara
import com.axel.legue.rickandmortykmm.data.network.CharactersServices
import com.axel.legue.rickandmortykmm.data.network.models.CharactersParam
import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult
import com.axel.legue.rickandmortykmm.data.network.utils.safeApiCall
import com.axel.legue.rickandmortykmm.domain.models.Character
import com.axel.legue.rickandmortykmm.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CharactersRepositoryImpl constructor(
    private val charactersServices: CharactersServices
) : CharactersRepository {

    override suspend fun fetchChara(charaParam: CharactersParam): Flow<NetworkResult<List<Character>>> {
        val characters = safeApiCall {
            val upcomingMovies = charactersServices.getCharacters(page = charaParam.page)
            upcomingMovies.toChara()
        }
        return flowOf(characters)
    }

}
