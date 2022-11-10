plugins {
    id("com.android.application")
    kotlin("android")
    id("io.gitlab.arturbosch.detekt") version "1.22.0-RC2"
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("${rootProject.rootDir}/config/detekt.yml")
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0-RC2")
}


tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        html.outputLocation.set(file("${rootProject.rootDir}/reports/detekt/detekt.html"))
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
        txt.outputLocation.set(file("${rootProject.rootDir}/reports/detekt/detekt.txt"))
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "11"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "11"
}

android {
    namespace = "com.axel.legue.rickandmortykmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.axel.legue.rickandmortykmm.android"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    with(AndroidDependencies) {
        implementation(ui)
        implementation(uiTooling)
        implementation(uiToolingPreview)
        implementation(foundation)
        implementation(material)
        implementation(activityCompose)
        implementation(navigationCompose)
        implementation(coroutinesAndroid)
        implementation(coil)
        implementation(koin)
        implementation(koinCompose)
    }
}