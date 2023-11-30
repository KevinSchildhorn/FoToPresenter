package com.kevinschildhorn.fotopresenter.data.datasources

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.get
import kotlin.test.Test
import kotlin.test.assertEquals

/**
Testing [CredentialsDataSource]
 **/
class CredentialsDataSourceTest {

    private val settings = MapSettings(
        KEY_HOSTNAME to "google.com",
        KEY_SHAREDFOLDER to "Public",
        KEY_USERNAME to "John",
        KEY_PASSWORD to "secret",
        KEY_AUTOCONNECT to true,
    )

    private val dataSource = CredentialsDataSource(settings)

    @Test
    fun `update Credentials`() {

        assertEquals("google.com", dataSource.hostname)
        assertEquals("John", dataSource.username)
        assertEquals("secret", dataSource.password)
        assertEquals("Public", dataSource.sharedFolder)

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
        assertEquals(newHostname, dataSource.hostname)
        assertEquals(newSharedFolder, dataSource.sharedFolder)
        assertEquals(newUsername, dataSource.username)
        assertEquals(newPassword, dataSource.password)

        assertEquals(newHostname, settings[KEY_HOSTNAME])
        assertEquals(newSharedFolder, settings[KEY_SHAREDFOLDER])
        assertEquals(newUsername, settings[KEY_USERNAME])
        assertEquals(newPassword, settings[KEY_PASSWORD])
        assertEquals(newShouldAutoConnect, settings[KEY_AUTOCONNECT])
    }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_SHAREDFOLDER = "sharedFolder"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_AUTOCONNECT = "autoConnect"
    }
}