package me.kevinschildhorn.common.architecture.data.datasources

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

/**
Stores Sign in Credentials from Shared Preferences
 **/
class CredentialsDataSource(private val settings: Settings) {

    var address: String
        get() = settings.getString(KEY_ADDRESS, "")
        set(value) {
            settings[KEY_ADDRESS] = value
        }

    var username: String
        get() = settings.getString(KEY_USERNAME, "")
        set(value) {
            settings[KEY_USERNAME] = value
        }

    var password: String
        get() = settings.getString(KEY_PASSWORD, "")
        set(value) {
            settings[KEY_PASSWORD] = value
        }

    companion object {
        const val DATABASE_NAME = "CredentialsDatabase"

        private const val KEY_ADDRESS = "address"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
