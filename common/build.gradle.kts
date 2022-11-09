import org.jetbrains.kotlin.gradle.plugin.mpp.Framework.BitcodeEmbeddingMode.BITCODE

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

group = "me.kevinschildhorn"
version = "1.0"

kotlin {
    cocoapods {
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        framework {
            baseName = "SharedFotoSDK"
            isStatic = false
            embedBitcode(BITCODE)
        }
    }

    android()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation ("io.insert-koin:koin-core:3.2.2")
                implementation("com.russhwolf:multiplatform-settings:1.0.0-RC")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("androidx.compose.ui:ui:1.3.0")
                implementation("androidx.compose.foundation:foundation:1.3.0")

                implementation("androidx.compose.material:material:1.3.0")
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel:2.5.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
                implementation("io.insert-koin:koin-android:3.2.2")
                implementation("androidx.security:security-crypto:1.1.0-alpha03")

            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}