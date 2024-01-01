plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jlleitschuh.gradle.ktlint")
    id("dev.icerock.mobile.multiplatform-resources")
    id("app.cash.sqldelight")
    kotlin("plugin.serialization")
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
        val commonMain by getting {
            dependencies {
                implementation(project(":atomik"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("br.com.devsrsouza.compose.icons:eva-icons:1.1.0")

                implementation("io.github.reactivecircus.cache4k:cache4k:0.12.0")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
                implementation("io.insert-koin:koin-core:3.4.0")
                implementation("androidx.security:security-crypto:1.1.0-alpha06")
                implementation("co.touchlab:kermit:1.2.2")
                implementation("co.touchlab:kermit-koin:1.2.2")
                implementation("com.russhwolf:multiplatform-settings:1.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:0.23.0") // for compose multiplatform
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.russhwolf:multiplatform-settings-test:1.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
                implementation("io.insert-koin:koin-test:3.4.0")
                implementation("app.cash.turbine:turbine:1.0.0")
                implementation("app.cash.sqldelight:sqlite-driver:2.0.1")
            }
        }

        val jvmMain by creating {
            dependsOn(commonMain)
            resources.srcDir("src/commonMain/resources")
            dependencies {
                implementation("com.hierynomus:smbj:0.11.5")
                implementation(compose.uiTooling)
                implementation("app.cash.sqldelight:sqlite-driver:2.0.1")
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
                implementation("app.cash.sqldelight:android-driver:2.0.1")
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
    multiplatformResourcesPackage = "com.kevinschildhorn.fotopresenter" // required
}

dependencies {
    ktlintRuleset("com.twitter.compose.rules:ktlint:0.0.26")
}

sqldelight {
    databases {
        create("PlaylistDatabase") {
            packageName.set("com.kevinschildhorn.fotopresenter")
        }
    }
}
