@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.build.konfig)
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }
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
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.navigation.compose)
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
                implementation(libs.lifecycle.viewmodel.compose)
                api(libs.coil)
                implementation(libs.file.kache)
                implementation(libs.compose.shimmer)
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
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
            }
        }

        val jvmMain by creating {
            dependsOn(commonMain)
            resources.srcDir("src/commonMain/resources")
            dependencies {
                implementation(libs.smbj)
                implementation(compose.uiTooling)
                implementation(libs.sqlite.driver)
                api(libs.coil.network.okhttp)
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
                implementation(libs.accompanist.systemuicontroller)
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
                implementation(libs.kotlinx.coroutines.swing)
            }
        }
        val desktopTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    afterEvaluate {
        tasks.named("check") {
            dependsOn(tasks.getByName("ktlintCheck"))
        }
    }
}

android {
    compileSdk =
        libs.versions.compileSdk
            .get()
            .toInt()
    namespace = "com.kevinschildhorn.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    defaultConfig {
        minSdk =
            libs.versions.minSdk
                .get()
                .toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlin {
        jvmToolchain(
            libs.versions.java
                .get()
                .toInt(),
        )
    }
}

dependencies {
    ktlintRuleset(libs.ktlint)
    androidTestImplementation(libs.ui.test.junit4.android)
    debugImplementation(libs.ui.test.manifest)
}

sqldelight {
    databases {
        create("PlaylistDatabase") {
            packageName.set("com.kevinschildhorn.fotopresenter")
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.kevinschildhorn.fotopresenter"
    generateResClass = auto
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

kover {
    currentProject {
        createVariant("custom") {
            add("debug")
            add("jvm")
        }
    }
    reports {
        total {
            // configuring report tasks
        }
        filters {
            excludes {
                packages("com.kevinschildhorn.fotopresenter.ui.screens.directory.composables")
                packages("com.kevinschildhorn.fotopresenter.ui.screens.login.composables")
                packages("com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables")
                packages("com.kevinschildhorn.fotopresenter.ui.screens.common.composables")
                packages("com.kevinschildhorn.fotopresenter.ui.atoms")
                packages("com.kevinschildhorn.fotopresenter.ui.composables")
                classes("ImageMetadataDataSource") // Uses Image Metadata library
                // Auto Generated SQLDelight
                classes("PlaylistDatabaseImpl")
            }
        }
    }
}

buildkonfig {
    packageName = "com.kevinschildhorn.fotopresenter"
    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.BOOLEAN,
            "USE_HTTP_IMAGES",
            project.properties["network_testing"] as? String ?: "",
        )
    }
}

ktlint {
    version.set("1.4.0")
    enableExperimentalRules.set(true)
    verbose.set(true)
    filter {
        exclude { it.file.path.contains("build/") }
    }
}
