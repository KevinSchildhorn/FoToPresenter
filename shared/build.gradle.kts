plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget()
    jvm("desktop")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":atomik"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("io.insert-koin:koin-core:3.4.0")
                implementation("androidx.security:security-crypto:1.1.0-alpha06")
                implementation("co.touchlab:kermit:1.2.2")
                implementation("co.touchlab:kermit-koin:1.2.2")
                implementation("com.russhwolf:multiplatform-settings:1.0.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.russhwolf:multiplatform-settings-test:1.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
                implementation("io.insert-koin:koin-test:3.4.0")
            }
        }

        val jvmMain by creating {
            dependsOn(commonMain)
            resources.srcDir("src/commonMain/resources")
            dependencies {
                implementation("com.hierynomus:smbj:0.13.0")
                implementation(compose.uiTooling)
            }
        }

        val androidMain by getting {
            dependsOn(jvmMain)
            resources.srcDirs("src/commonMain/resources")
            dependencies {
                api("androidx.activity:activity-compose:1.8.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.12.0")
                implementation("io.github.kevinschildhorn:atomik:0.0.6")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            resources.srcDirs("src/commonMain/resources")
        }
        val desktopMain by getting {
            dependsOn(jvmMain)
            dependencies {
                resources.srcDirs("src/commonMain/resources")
                implementation(compose.preview)
                implementation(compose.desktop.common)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.kevinschildhorn.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
