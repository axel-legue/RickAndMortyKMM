package com.axel.legue.rickandmortykmm.domain.repositories

import com.axel.legue.rickandmortykmm.data.network.models.RequestParam
import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult
import com.axel.legue.rickandmortykmm.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun fetchChara(charaParam: RequestParam): Flow<NetworkResult<List<Character>>>
}
