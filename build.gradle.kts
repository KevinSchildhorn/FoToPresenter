plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
    id("org.jlleitschuh.gradle.ktlint").version("12.0.2").apply(false)
    id("dev.icerock.mobile.multiplatform-resources").version("0.23.0").apply(false)
    id("app.cash.sqldelight").version("2.0.1").apply(false)
    kotlin("plugin.serialization").version("1.9.21").apply(false)
}
