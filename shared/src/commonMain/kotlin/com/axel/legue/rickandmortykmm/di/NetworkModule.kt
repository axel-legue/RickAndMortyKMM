package com.axel.legue.rickandmortykmm.di

import com.axel.legue.rickandmortykmm.data.datasources.CharactersRepositoryImpl
import com.axel.legue.rickandmortykmm.data.network.CharactersServiceImpl
import com.axel.legue.rickandmortykmm.data.network.CharactersServices
import com.axel.legue.rickandmortykmm.domain.repositories.CharactersRepository
import com.axel.legue.rickandmortykmm.presentation.SharedCharactersPresenter
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

    single<CharactersServices> { CharactersServiceImpl(httpClient = get()) }
    single<CharactersRepository> { CharactersRepositoryImpl(charactersServices = get()) }
    single { SharedCharactersPresenter(upcomingMoviesRepository = get()) }
}