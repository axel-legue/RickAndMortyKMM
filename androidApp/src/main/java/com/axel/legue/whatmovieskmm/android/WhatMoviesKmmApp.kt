package com.axel.legue.whatmovieskmm.android

import android.app.Application
import com.axel.legue.whatmovieskmm.Helper
import io.github.aakira.napier.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class WhatMoviesKmmApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Helper().initKoin(enableNetworkLogs = BuildConfig.DEBUG)
        if (BuildConfig.DEBUG) Napier.base(DebugAntilog())
    }
}
