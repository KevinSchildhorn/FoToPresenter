package me.kevinschildhorn.common.architecture.data.datasources

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
        KEY_USERNAME to "John",
        KEY_PASSWORD to "secret"
    )

    private val dataSource = CredentialsDataSource(settings)

    @Test
    fun `update Credentials`() {

        assertEquals("google.com", dataSource.hostname)
        assertEquals("John", dataSource.username)
        assertEquals("secret", dataSource.password)

        val newHostname = "sample.com"
        val newUsername = "Will"
        val newPassword = "qwerty"
        dataSource.apply {
            hostname = newHostname
            username = newUsername
            password = newPassword
        }
        assertEquals(newHostname, dataSource.hostname)
        assertEquals(newUsername, dataSource.username)
        assertEquals(newPassword, dataSource.password)

        assertEquals(newHostname, settings[KEY_HOSTNAME])
        assertEquals(newUsername, settings[KEY_USERNAME])
        assertEquals(newPassword, settings[KEY_PASSWORD])
    }

    companion object {
        private const val KEY_HOSTNAME = "hostName"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
