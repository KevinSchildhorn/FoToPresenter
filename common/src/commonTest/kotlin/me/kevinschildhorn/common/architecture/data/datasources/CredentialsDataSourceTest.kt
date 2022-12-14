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
        KEY_PORT to 123,
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
        val newPort = 123
        val newUsername = "Will"
        val newPassword = "qwerty"
        dataSource.apply {
            hostname = newHostname
            port = newPort
            username = newUsername
            password = newPassword
        }
        assertEquals(newHostname, dataSource.hostname)
        assertEquals(newPort, dataSource.port)
        assertEquals(newUsername, dataSource.username)
        assertEquals(newPassword, dataSource.password)

        assertEquals(newHostname, settings[KEY_HOSTNAME])
        assertEquals(newPort, settings[KEY_PORT])
        assertEquals(newUsername, settings[KEY_USERNAME])
        assertEquals(newPassword, settings[KEY_PASSWORD])
    }

    companion object {
        private const val KEY_HOSTNAME = "hostname"
        private const val KEY_PORT = "port"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
