import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.kermit)
                implementation(libs.multiplatform.settings)

                implementation(project(":shared"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Exe, TargetFormat.Deb)
            packageName = "FotoPresenter"
            packageVersion = "1.0.0"
            modules("java.sql")
            macOS {
                //iconFile.set(project.file("src/desktopMain/resources/icons/icon.icns"))
            }

            windows {
                // Windows uses .ico files
                //iconFile.set(project.file("src/desktopMain/resources/icons/icon.ico"))
            }
        }
    }
}
