plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
            implementation("co.touchlab:kermit:1.2.2")
            implementation("co.touchlab:kermit-koin:1.2.2")
            implementation("com.russhwolf:multiplatform-settings:1.0.0")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        }
        androidMain.dependencies {
        }
    }
}

android {
    namespace = "com.kevinschildhorn.fotopresenter.shared"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
}
