package com.axel.legue.rickandmortykmm

import com.axel.legue.rickandmortykmm.di.networkModule
import com.axel.legue.rickandmortykmm.presentation.SharedCharactersPresenter
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = true, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(networkModule(enableNetworkLogs))
    }

fun KoinApplication.Companion.start(): KoinApplication = initKoin {}

val Koin.moviePresenter: SharedCharactersPresenter
    get() = get()



