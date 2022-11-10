package com.axel.legue.rickandmortykmm.android

import android.app.Application
import com.axel.legue.rickandmortykmm.initKoin
import io.github.aakira.napier.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext

class RickAndMortyKMMApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(androidContext = this@RickAndMortyKMMApp)
        }
        if (BuildConfig.DEBUG) Napier.base(DebugAntilog())
    }
}
