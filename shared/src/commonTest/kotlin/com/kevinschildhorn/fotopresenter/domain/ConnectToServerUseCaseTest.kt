package com.kevinschildhorn.fotopresenter.domain

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.testingModule
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
Testing [ConnectToServerUseCase]
 **/
class ConnectToServerUseCaseTest : KoinTest {

    private val useCase: ConnectToServerUseCase by inject()

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
    fun `connect to server success`() = runBlocking {
        val loginCredentials = LoginCredentials(
            "192.168.1.1",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = false,
        )
        val result = useCase(loginCredentials)
        assertTrue(result, "Failed to Connect to Server")
    }

    @Test
    fun `connect to server failure`() = runBlocking {
        val loginCredentials = LoginCredentials(
            "google.com",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = false,
        )
        val result = useCase(loginCredentials)
        assertFalse(result, "Should not have Connected to Server")
    }

    @Test
    fun `connect to server throw`() = runBlocking {
        val loginCredentials = LoginCredentials(
            "throw",
            "admin",
            "password",
            "Public",
            shouldAutoConnect = false,
        )
        val result = useCase(loginCredentials)
        assertFalse(result, "Should not have Connected to Server")
    }
}