package com.axel.legue.rickandmortykmm.data.datasources

import com.axel.legue.rickandmortykmm.data.mappers.toEpisodes
import com.axel.legue.rickandmortykmm.data.network.ApiService
import com.axel.legue.rickandmortykmm.data.network.models.RequestParam
import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult
import com.axel.legue.rickandmortykmm.data.network.utils.safeApiCall
import com.axel.legue.rickandmortykmm.domain.models.Episode
import com.axel.legue.rickandmortykmm.domain.repositories.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class EpisodesRepositoryImpl constructor(
    private val apiService: ApiService
) : EpisodesRepository {

    override suspend fun fetchEpisodes(param: RequestParam): Flow<NetworkResult<List<Episode>>> {
        val characters = safeApiCall {
            val episodes = apiService.getEpisodes(page = param.page)
            episodes.toEpisodes()
        }
        return flowOf(characters)
    }
}
