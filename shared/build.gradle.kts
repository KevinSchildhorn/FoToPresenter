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
            api("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-15")
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
