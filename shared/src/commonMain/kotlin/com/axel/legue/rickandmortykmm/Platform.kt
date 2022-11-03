package com.axel.legue.rickandmortykmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform