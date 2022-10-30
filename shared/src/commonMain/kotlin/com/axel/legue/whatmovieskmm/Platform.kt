package com.axel.legue.whatmovieskmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform