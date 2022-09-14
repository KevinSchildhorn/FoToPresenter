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
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                val compose_version = "1.2.1"
                api("androidx.appcompat:appcompat:1.5.0")
                api("androidx.core:core-ktx:1.8.0")
                implementation("androidx.compose.ui:ui:$compose_version")
                implementation("androidx.compose.material:material:$compose_version")
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
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}