package me.kevinschildhorn.common.architecture.data.repositories

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import me.kevinschildhorn.common.architecture.data.datasources.CredentialsDataSource
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
Testing [CredentialsRepository]
 **/
class CredentialsRepositoryTest : KoinTest {

    private val repository: CredentialsRepository by inject()

    @BeforeTest
    fun startTest() {
        startKoin {
            modules(
                module {
                    single<Settings> { MapSettings() }
                    single { CredentialsDataSource(get()) }
                    single { CredentialsRepository(get()) }
                }
            )
        }
    }

    @Test
    fun `save credentials`() {
        val hostname = "google.com"
        val port = 123
        val username = "John"
        val password = "secret"

        repository.saveCredentials(hostname, port, username, password, true)
        val credentials = repository.fetchCredentials()
        assertEquals(hostname, credentials.hostname)
        assertEquals(port, credentials.port)
        assertEquals(username, credentials.username)
        assertEquals(password, credentials.password)
    }
}
