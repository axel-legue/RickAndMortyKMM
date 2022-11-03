package com.axel.legue.rickandmortykmm.domain.repositories

import com.axel.legue.rickandmortykmm.data.network.models.CharactersParam
import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult
import com.axel.legue.rickandmortykmm.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun fetchChara(charaParam: CharactersParam): Flow<NetworkResult<List<Character>>>
}
