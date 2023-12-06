package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

private val baseLogger = Logger(LoggerConfig.default)

fun testingModule(settings: MapSettings = MapSettings()) =
    module {
        single<NetworkHandler> { MockNetworkHandler }
        single<Settings> { settings }
    } + commonModule
