package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.testingModule
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
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
    fun `save credentials`() {
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
            shouldAutoConnect = shouldAutoConnect
        )
        val credentials = repository.fetchCredentials()
        assertEquals(hostname, credentials.hostname)
        assertEquals(sharedFolder, credentials.sharedFolder)
        assertEquals(username, credentials.username)
        assertEquals(password, credentials.password)
        assertEquals(shouldAutoConnect, credentials.shouldAutoConnect)
    }
}