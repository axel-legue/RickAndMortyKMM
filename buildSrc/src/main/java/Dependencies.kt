object Versions {
    const val koin = "3.2.0"
    const val ktor = "2.1.1"
    const val dateTime = "0.4.0"
    const val coroutines = "1.6.4"
    const val napier = "2.4.0"
    const val ui = "1.3.0"
    const val uiTooling = "1.3.0"
    const val uiToolingPreview = "1.3.0"
    const val foundation = "1.3.0"
    const val material = "1.3.0"
    const val activityCompose = "1.6.1"
    const val navigationCompose = "2.5.3"
}

object MultiPlatformDependencies {

    object DateTime {
        const val core = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTime}"
    }

    object Coroutine {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val logging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val mock = "io.ktor:ktor-client-mock:${Versions.ktor}"
    }

    object Napier {
        const val core = "io.github.aakira:napier:${Versions.napier}"
    }
}

object AndroidDependencies {
    const val ui = "androidx.compose.ui:ui:${Versions.ui}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.uiTooling}"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.uiToolingPreview}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.foundation}"
    const val material = "androidx.compose.material:material:${Versions.material}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}