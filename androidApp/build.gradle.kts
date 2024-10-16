import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.java

plugins {
    id("com.android.application")
    kotlin("multiplatform")
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.koin.android)
                implementation(libs.firebase.crashlytics)
                implementation(libs.accompanist.permissions)
                implementation(libs.kermit)

            }
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.kevinschildhorn"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.kevinschildhorn.fotopresenter"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlin {
        jvmToolchain(libs.versions.java.get().toInt())
    }
}
