
plugins {
    kotlin("multiplatform")
    alias(libs.plugins.kotlin.compose)
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                //implementation(compose.desktop.currentOs)
                implementation("io.insert-koin:koin-core:3.5.3")
                implementation("co.touchlab:kermit:2.0.4")
                implementation("com.russhwolf:multiplatform-settings:1.1.1")

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
