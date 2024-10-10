
plugins {
    kotlin("multiplatform")
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                //implementation(compose.desktop.currentOs)
                implementation(libs.koin.core)
                implementation(libs.kermit)
                implementation(libs.multiplatform.settings)

                implementation(project(":shared"))
            }
        }
    }
}
/*
compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinMultiplatformComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}*/
