plugins {
    id("org.jetbrains.compose") version "1.1.0"
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("com.google.relay") version "0.3.00"
}

group = "me.kevinschildhorn"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    val compose_version = "1.2.1"

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha02")
    implementation("androidx.compose.ui:ui:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")

    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.foundation:foundation:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.runtime:runtime:$compose_version")
    implementation("androidx.activity:activity-compose:1.6.0")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "me.kevinschildhorn.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
        }
    }
}