package com.axel.legue.whatmovieskmm

import com.axel.legue.whatmovieskmm.di.networkModule
import org.koin.core.context.startKoin

class Helper {
    fun initKoin(enableNetworkLogs: Boolean = false) {
        startKoin {
            modules(networkModule(enableNetworkLogs))
        }
    }
}



