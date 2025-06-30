package com.kevinschildhorn.fotopresenter.data.datasources

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

/**
Stores Sign in Credentials from Shared Preferences
 **/
class CredentialsDataSource(
    private val settings: Settings,
) {
    var hostname: String
        get() = settings.getString(KEY_HOSTNAME, "")
        set(value) {
            settings[KEY_HOSTNAME] = value
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

    var sharedFolder: String
        get() = settings.getString(KEY_SHAREDFOLDER, "")
        set(value) {
            settings[KEY_SHAREDFOLDER] = value
        }

    var shouldAutoConnect: Boolean
        get() = settings.getBoolean(KEY_AUTOCONNECT, false)
        set(value) {
            settings[KEY_AUTOCONNECT] = value
        }

    companion object {
        const val DATABASE_NAME = "CredentialsDatabase"

        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_SHAREDFOLDER = "sharedFolder"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_AUTOCONNECT = "autoConnect"
    }
}
