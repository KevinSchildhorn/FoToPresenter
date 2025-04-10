package com.kevinschildhorn.fotopresenter.data.datasources

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.get
import kotlin.test.Test
import kotlin.test.assertEquals

/**
Testing [CredentialsDataSource]
 **/
class CredentialsDataSourceTest {
    private val settings =
        MapSettings(
            KEY_HOSTNAME to "google.com",
            KEY_SHAREDFOLDER to "Public",
            KEY_USERNAME to "John",
            KEY_PASSWORD to "secret",
            KEY_AUTOCONNECT to true,
        )

    private val dataSource = CredentialsDataSource(settings)

    @Test
    fun `updateCredentials`() {
        assertEquals(expected = "google.com", actual = dataSource.hostname)
        assertEquals(expected = "John", actual = dataSource.username)
        assertEquals(expected = "secret", actual = dataSource.password)
        assertEquals(expected = "Public", actual = dataSource.sharedFolder)

        val newHostname = "sample.com"
        val newUsername = "Will"
        val newPassword = "qwerty"
        val newSharedFolder = "Private"
        val newShouldAutoConnect = false
        dataSource.apply {
            hostname = newHostname
            username = newUsername
            password = newPassword
            sharedFolder = newSharedFolder
            shouldAutoConnect = newShouldAutoConnect
        }
        assertEquals(expected = newHostname, actual = dataSource.hostname)
        assertEquals(expected = newSharedFolder, actual = dataSource.sharedFolder)
        assertEquals(expected = newUsername, actual = dataSource.username)
        assertEquals(expected = newPassword, actual = dataSource.password)

        assertEquals(expected = newHostname, actual = settings[KEY_HOSTNAME])
        assertEquals(expected = newSharedFolder, actual = settings[KEY_SHAREDFOLDER])
        assertEquals(expected = newUsername, actual = settings[KEY_USERNAME])
        assertEquals(expected = newPassword, actual = settings[KEY_PASSWORD])
        assertEquals(expected = newShouldAutoConnect, actual = settings[KEY_AUTOCONNECT])
    }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_SHAREDFOLDER = "sharedFolder"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_AUTOCONNECT = "autoConnect"
    }
}
