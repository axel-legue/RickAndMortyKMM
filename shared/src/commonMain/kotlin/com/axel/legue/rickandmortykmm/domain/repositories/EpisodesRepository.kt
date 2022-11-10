package com.axel.legue.rickandmortykmm.domain.repositories

import com.axel.legue.rickandmortykmm.data.network.models.RequestParam
import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult
import com.axel.legue.rickandmortykmm.domain.models.Character
import com.axel.legue.rickandmortykmm.domain.models.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    suspend fun fetchEpisodes(param: RequestParam): Flow<NetworkResult<List<Episode>>>
}
