@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import kotlin.collections.plusAssign

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.sqldelight)
    //alias(libs.plugins.crashlytics)
    alias(libs.plugins.resources)
    alias(libs.plugins.serialization)
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget()
    jvm("desktop")
    /*
    listOf( TODO re-add
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }*/

    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":atomik"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.eva.icons)
                implementation(libs.cache4k)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.koin.core)
                implementation(libs.security.crypto)
                implementation(libs.kermit)
                implementation(libs.kermit.koin)
                implementation(libs.multiplatform.settings)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kim)
                api(libs.resources)
                api(libs.resources.compose) // for compose multiplatform
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.multiplatform.settings.test)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.koin.test)
                implementation(libs.turbine)
                implementation(libs.sqlite.driver)
            }
        }

        val jvmMain by creating {
            dependsOn(commonMain)
            resources.srcDir("src/commonMain/resources")
            dependencies {
                implementation(libs.smbj)
                implementation(compose.uiTooling)
                implementation(libs.sqlite.driver)
            }
        }

        val androidMain by getting {
            dependsOn(jvmMain)
            resources.srcDirs("src/commonMain/resources")
            dependencies {
                api(libs.activity.compose)
                api(libs.appcompat)
                api(libs.core.ktx)
                implementation(libs.atomik)
                implementation(libs.android.driver)
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
            resources.srcDirs("src/commonMain/resources")
            implementation("app.cash.sqldelight:native-driver:2.0.1")
        }*/
        val desktopMain by getting {
            dependsOn(jvmMain)
            dependencies {
                resources.srcDirs("src/commonMain/resources")
                implementation(compose.preview)
                implementation(compose.desktop.common)
            }
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
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

multiplatformResources {
    resourcesPackage.set("com.kevinschildhorn.fotopresenter") // required
    resourcesClassName.set("SharedRes")
}

dependencies {
    ktlintRuleset(libs.ktlint)
}

sqldelight {
    databases {
        create("PlaylistDatabase") {
            packageName.set("com.kevinschildhorn.fotopresenter")
        }
    }
}
