package me.kevinschildhorn.common

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import me.kevinschildhorn.common.layers.data.datasources.CredentialsDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

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
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            ),
            commit = false
        )
    }
}