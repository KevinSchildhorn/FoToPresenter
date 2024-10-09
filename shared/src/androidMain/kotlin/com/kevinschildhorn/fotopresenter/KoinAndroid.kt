package com.kevinschildhorn.fotopresenter

import android.app.Application
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import app.cash.sqldelight.db.SqlDriver
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.kevinschildhorn.fotopresenter.ui.shared.DriverFactory
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

fun startKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(commonModule, platformModule)
    }
}

internal actual val platformModule: Module = module {
    single<Settings> {
        SharedPreferencesSettings(
            delegate = EncryptedSharedPreferences.create(
                get(),
                CredentialsDataSource.DATABASE_NAME,
                MasterKey.Builder(get())
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            ),
            commit = false,
        )
    }
    single<NetworkHandler> {
        SMBJHandler
    }
    single<SqlDriver> { DriverFactory(context = get()).createDriver() }
}

@OptIn(KoinInternalApi::class)
fun KoinApplication.androidContext(androidContext: Context): KoinApplication {
    if (koin.logger.isAt(Level.INFO)) {
        koin.logger.info("[init] declare Android Context")
    }

    if (androidContext is Application) {
        koin.loadModules(
            listOf(
                module {
                    single { androidContext } binds arrayOf(Context::class, Application::class)
                },
            ),
        )
    } else {
        koin.loadModules(
            listOf(
                module {
                    single { androidContext }
                },
            ),
        )
    }

    return this
}
