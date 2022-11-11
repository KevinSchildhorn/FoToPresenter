package me.kevinschildhorn.common

import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import me.kevinschildhorn.common.layers.data.datasources.CredentialsDataSource
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.CFBridgingRetain
import platform.Security.kSecAttrAccessible
import platform.Security.kSecAttrAccessibleAfterFirstUnlock
import platform.Security.kSecAttrService

@Suppress("unused")
fun startKoin() {
    startKoin {
        modules(commonModule, platformModule)
    }
}

internal actual val platformModule: Module = module {
    single<Settings> {
        KeychainSettings(
            defaultProperties = arrayOf(
                kSecAttrService to CFBridgingRetain(CredentialsDataSource.DATABASE_NAME),
                kSecAttrAccessible to kSecAttrAccessibleAfterFirstUnlock,
            )
        )
    }
}
