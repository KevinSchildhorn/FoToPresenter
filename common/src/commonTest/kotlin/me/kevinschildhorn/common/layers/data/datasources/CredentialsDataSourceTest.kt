package me.kevinschildhorn.common.layers.data.datasources

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.get
import kotlin.test.Test
import kotlin.test.assertEquals

/**
Testing [CredentialsDataSource]
 **/
class CredentialsDataSourceTest {

    private val settings = MapSettings(
        KEY_ADDRESS to "google.com",
        KEY_USERNAME to "John",
        KEY_PASSWORD to "secret"
    )

    private val dataSource = CredentialsDataSource(settings)

    @Test
    fun `update Credentials`() {

        assertEquals("google.com", dataSource.address)
        assertEquals("John", dataSource.username)
        assertEquals("secret", dataSource.password)

        val newAddress = "sample.com"
        val newUsername = "Will"
        val newPassword = "qwerty"
        dataSource.apply {
            address = newAddress
            username = newUsername
            password = newPassword
        }
        assertEquals(newAddress, dataSource.address)
        assertEquals(newUsername, dataSource.username)
        assertEquals(newPassword, dataSource.password)

        assertEquals(newAddress, settings[KEY_ADDRESS])
        assertEquals(newUsername, settings[KEY_USERNAME])
        assertEquals(newPassword, settings[KEY_PASSWORD])
    }

    companion object {
        private const val KEY_ADDRESS = "address"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}
