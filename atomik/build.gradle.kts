@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.gradle.kotlin.dsl.add
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.ktlint)
}
/*
// Exclude compose from iOS
plugins.removeAll { it is ComposeCompilerKotlinSupportPlugin }
class ComposeNoNativePlugin : org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin by ComposeCompilerKotlinSupportPlugin() {
    override fun isApplicable(kotlinCompilation: org.jetbrains.kotlin.gradle.plugin.KotlinCompilation<*>): Boolean {
        return when (kotlinCompilation.target.platformType) {
            org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.native -> false
            else -> ComposeCompilerKotlinSupportPlugin().isApplicable(kotlinCompilation)
        }
    }
}
apply<ComposeNoNativePlugin>() // Re-adding Compose Compilers only for non-native environments
*/
group = "io.github.kevinschildhorn"
version = "0.0.6"
android {
    namespace = "com.kevinschildhorn.atomik"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "15"
    }
}

kotlin {
    explicitApi()
    applyDefaultHierarchyTemplate()
    androidTarget {
        publishLibraryVariants("debug", "release")
    }
    jvm("desktop")
    /*
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "atomik"
        }
    }*/
    jvm("desktop")

    jvmToolchain(15)
    sourceSets {
        val commonMain by getting {

            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(libs.kermit)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                api(libs.resources)
                api(libs.resources.compose) // for compose multiplatform
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by creating {
            dependsOn(commonMain)
        }

        val androidMain by getting {
            dependsOn(jvmMain)
            languageSettings.optIn("kotlin.ExperimentalMultiplatform")
            dependencies {
                api(libs.appcompat)
                api(libs.core.ktx)
            }
        }
        /*
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }*/

        val desktopMain by getting {
            dependsOn(jvmMain)
            languageSettings.optIn("kotlin.ExperimentalMultiplatform")
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    namespace = "com.kevinschildhorn.atomik"

    compileSdk = 34

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_15
        targetCompatibility = JavaVersion.VERSION_15
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
}
