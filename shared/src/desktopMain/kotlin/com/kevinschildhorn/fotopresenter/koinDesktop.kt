package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("unused")
fun startKoin() {
    org.koin.core.context.startKoin {
        modules(commonModule, platformModule)
    }
}

internal actual val platformModule: Module = module {
    single<Settings> {
        PreferencesSettings(get())
    }
    single<NetworkHandler> {
        SMBJHandler
    }
}