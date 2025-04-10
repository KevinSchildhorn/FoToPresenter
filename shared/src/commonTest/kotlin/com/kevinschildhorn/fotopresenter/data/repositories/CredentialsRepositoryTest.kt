package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.testingModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import kotlin.test.AfterTest
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
            modules(testingModule())
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `save_credentials`() {
        val hostname = "google.com"
        val sharedFolder = "Public"
        val username = "John"
        val password = "secret"
        val shouldAutoConnect = true

        repository.saveCredentials(
            hostname = hostname,
            username = username,
            password = password,
            sharedFolder = sharedFolder,
            shouldAutoConnect = shouldAutoConnect,
        )
        val credentials = repository.fetchCredentials()
        assertEquals(expected = hostname, actual = credentials.hostname)
        assertEquals(expected = sharedFolder, actual = credentials.sharedFolder)
        assertEquals(expected = username, actual = credentials.username)
        assertEquals(expected = password, actual = credentials.password)
        assertEquals(expected = shouldAutoConnect, actual = credentials.shouldAutoConnect)
    }

    @Test
    fun `clear_auto-connect`() {
        repository.saveCredentials(
            hostname = "google.com",
            username = "secret",
            password = "password",
            sharedFolder = "Public",
            shouldAutoConnect = true,
        )
        var credentials = repository.fetchCredentials()
        assertEquals(expected = true, actual = credentials.shouldAutoConnect)

        repository.clearAutoConnect()
        credentials = repository.fetchCredentials()

        assertEquals(expected = false, actual = credentials.shouldAutoConnect)
    }

    @Test
    fun `get_credentials_empty`() {
        val credentials = repository.fetchCredentials()
        assertEquals(expected = "", actual = credentials.hostname)
        assertEquals(expected = "", actual = credentials.sharedFolder)
        assertEquals(expected = "", actual = credentials.username)
        assertEquals(expected = "", actual = credentials.password)
        assertEquals(expected = false, actual = credentials.shouldAutoConnect)
    }
}
