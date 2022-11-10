package com.axel.legue.rickandmortykmm.di

import com.axel.legue.rickandmortykmm.data.datasources.CharactersRepositoryImpl
import com.axel.legue.rickandmortykmm.data.datasources.EpisodesRepositoryImpl
import com.axel.legue.rickandmortykmm.data.network.ApiService
import com.axel.legue.rickandmortykmm.data.network.ApiServiceImpl
import com.axel.legue.rickandmortykmm.domain.repositories.CharactersRepository
import com.axel.legue.rickandmortykmm.domain.repositories.EpisodesRepository
import com.axel.legue.rickandmortykmm.presentation.SharedCharactersPresenter
import com.axel.legue.rickandmortykmm.presentation.SharedEpisodesPresenter
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun networkModule(enableNetworkLogs: Boolean = true) = module {

    // Create Ktor http client provided by DI
    single {
        HttpClient {
            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.e(message = message, tag = "Http Client")
                        }
                    }
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    single<ApiService> { ApiServiceImpl(httpClient = get()) }
    single<CharactersRepository> { CharactersRepositoryImpl(apiService = get()) }
    single<EpisodesRepository> { EpisodesRepositoryImpl(apiService = get()) }
    single { SharedCharactersPresenter(characterRepository = get()) }
    single { SharedEpisodesPresenter(episodeRepository = get()) }
}