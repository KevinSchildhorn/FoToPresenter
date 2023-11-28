plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

group = "me.kevinschildhorn"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    implementation(project(":mobileMain"))
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0-rc01")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.5.4")
    implementation("androidx.compose.material:material:1.5.4")
    implementation("androidx.compose.runtime:runtime:1.5.4")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("io.insert-koin:koin-android:3.4.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
}
