plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.1").apply(false)
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("installLocalGitHook", type = Copy::class) {
    from("${rootProject.rootDir}/scripts/pre-commit")
    into("${rootProject.rootDir}/.git/hooks") {
        fileMode = 493
    }
    destinationDir = File("${rootProject.rootDir}/.git/hooks")
}

tasks.register("filePermission", type = Exec::class) {
    dependsOn(":installLocalGitHook")
    commandLine = listOf("chmod", "755", "${rootProject.rootDir}/.git/hooks/pre-commit")
}


afterEvaluate {
    tasks["clean"].dependsOn(":filePermission")
}

