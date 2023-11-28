import org.jetbrains.kotlin.gradle.plugin.mpp.Framework.BitcodeEmbeddingMode.BITCODE

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.github.ben-manes.versions") version "0.43.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
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
                implementation(kotlin("stdlib-common"))
                implementation("co.touchlab:kermit:1.2.2")
                implementation("co.touchlab:kermit-koin:1.2.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.insert-koin:koin-core:3.4.0")
                implementation("com.russhwolf:multiplatform-settings:1.0.0")
                api("io.github.kevinschildhorn:atomik:0.0.6")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.russhwolf:multiplatform-settings-test:1.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
                implementation("io.insert-koin:koin-test:3.4.0")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.12.0")

                implementation("androidx.compose.ui:ui:1.5.4")
                implementation("androidx.compose.foundation:foundation:1.5.4")
                implementation("androidx.compose.material:material:1.5.4")
                implementation("androidx.compose.runtime:runtime:1.5.4")

                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
                implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
                implementation("io.insert-koin:koin-android:3.4.0")
                implementation("androidx.security:security-crypto:1.1.0-alpha06")
                //implementation("commons-net:commons-net:3.9.0")
                implementation("com.hierynomus:smbj:0.13.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
                implementation("io.insert-koin:koin-test-junit4:3.2.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("com.russhwolf:multiplatform-settings-test:1.0.0")
            }
        }
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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
}
