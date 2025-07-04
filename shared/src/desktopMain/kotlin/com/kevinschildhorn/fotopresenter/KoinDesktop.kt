package com.kevinschildhorn.fotopresenter

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import co.touchlab.kermit.koin.kermitLoggerModule
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.defaultNetworkHandler
import com.kevinschildhorn.fotopresenter.ui.shared.DriverFactory
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("unused")
fun startKoin() {
    org.koin.core.context.startKoin {
        logger(
            KermitKoinLogger(Logger),
        )
        modules(commonModule, kermitLoggerModule(Logger), platformModule)
    }
}

internal actual val platformModule: Module =
    module {
        single<Settings> {
            PreferencesSettings(get())
        }
        single<NetworkHandler> {
            defaultNetworkHandler
        }
        single<SqlDriver> { DriverFactory().createDriver() }
    }
