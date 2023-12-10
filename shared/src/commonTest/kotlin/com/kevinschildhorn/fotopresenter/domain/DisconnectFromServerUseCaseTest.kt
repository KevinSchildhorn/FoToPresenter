package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.domain.connection.DisconnectFromServerUseCase
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertFalse

/**
Testing [DisconnectFromServerUseCase]
 **/
class DisconnectFromServerUseCaseTest : KoinTest {
    private val useCase: DisconnectFromServerUseCase by inject()

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
    fun `logout Success`() =
        runBlocking {
            useCase()
            assertFalse(MockNetworkHandler.isConnected, "Failed to Disconnect")
        }

    @Test
    fun `logout Success Safe`() =
        runBlocking {
            MockNetworkHandler.disconnect()
            assertFalse(MockNetworkHandler.isConnected, "Failed to Disconnect")
            useCase()
            assertFalse(MockNetworkHandler.isConnected, "Failed to Disconnect")
        }
}
