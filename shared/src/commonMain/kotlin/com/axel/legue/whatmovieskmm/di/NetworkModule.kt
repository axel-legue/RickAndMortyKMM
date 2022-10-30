package com.axel.legue.whatmovieskmm.di

import com.axel.legue.whatmovieskmm.data.datasources.UpcomingMoviesRepositoryImpl
import com.axel.legue.whatmovieskmm.data.network.UpcomingMoviesServiceImpl
import com.axel.legue.whatmovieskmm.domain.utils.API_KEY
import com.axel.legue.whatmovieskmm.domain.utils.BASE_URL
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun networkModule(enableNetworkLogs: Boolean) = module {

    // Create Ktor http client provided by DI
    single {
        HttpClient(get()) {
            defaultRequest {
                url {
                    host = BASE_URL
                    url { protocol = URLProtocol.HTTPS }
                    parameters.append("api_key", API_KEY)
                    parameters.append("language", "fr-en") // Todo add this param in app settings
                }
            }

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

    single { UpcomingMoviesServiceImpl(httpClient = get()) }
    single { UpcomingMoviesRepositoryImpl(upcomingMoviesService = get()) }
}