package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.domain.connection.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
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
    fun `connect to server success`() =
        runBlocking {
            val loginCredentials =
                LoginCredentials(
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
    fun `connect to server failure`() =
        runBlocking {
            val loginCredentials =
                LoginCredentials(
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
    fun `connect to server throw`() =
        runBlocking {
            val loginCredentials =
                LoginCredentials(
                    "throw",
                    "admin",
                    "password",
                    "Public",
                    shouldAutoConnect = false,
                )
            val result = useCase(loginCredentials)
            assertFalse(result, "Should not have Connected to Server, should have thrown")
        }
}
