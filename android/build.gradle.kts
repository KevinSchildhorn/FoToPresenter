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
    implementation(project(":common"))
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.2")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03")
    implementation("androidx.compose.ui:ui:1.3.2")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.runtime:runtime:1.3.2")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("io.insert-koin:koin-android:3.3.0")
    implementation(project(":atomik"))
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.2")
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
