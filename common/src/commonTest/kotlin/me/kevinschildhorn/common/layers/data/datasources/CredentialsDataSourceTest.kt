package me.kevinschildhorn.common.layers.data.datasources

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.get
import me.kevinschildhorn.common.layers.domain.SaveCredentialsUseCase
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

        assertEquals(dataSource.address, "google.com", "")
        assertEquals(dataSource.username, "John")
        assertEquals(dataSource.password, "secret")

        val newAddress = "sample.com"
        val newUsername = "Will"
        val newPassword = "qwerty"
        dataSource.apply {
            address = newAddress
            username = newUsername
            password = newPassword
        }
        assertEquals(dataSource.address, newAddress)
        assertEquals(dataSource.username, newUsername)
        assertEquals(dataSource.password, newPassword)

        assertEquals(settings[KEY_ADDRESS], newAddress)
        assertEquals(settings[KEY_USERNAME], newUsername)
        assertEquals(settings[KEY_PASSWORD], newPassword)
    }

    companion object {
        private const val KEY_ADDRESS = "address"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }
}