package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.fail

/**
Testing [RetrieveImageDirectoriesUseCase]
 **/
class RetrieveImageDirectoriesUseCaseTest : KoinTest {
    private val useCase: RetrieveImageDirectoriesUseCase by inject()

    @BeforeTest
    fun startTest() =
        runBlocking {
            startKoin {
                modules(testingModule())
            }
            MockNetworkHandler.connectSuccessfully()
        }

    @AfterTest
    fun tearDown() =
        runBlocking {
            stopKoin()
            MockNetworkHandler.disconnect()
        }

    @Test
    fun `receive directory content success`() =
        runBlocking {
            val details = MockNetworkDirectoryDetails("", 1)
            val result = useCase(details)
            assertEquals(6, result.count())
        }

    @Test
    fun `receive directory content only directories`() =
        runBlocking {
            val details = MockNetworkDirectoryDetails("Directories", 1)
            val result = useCase(details)
            assertEquals(0, result.count())
        }

    @Test
    fun `receive directory content failure`() =
        runBlocking {
            val details = MockNetworkDirectoryDetails("nonExistant", 1)
            val result = useCase(details)
            assertEquals(0, result.count())
        }

    @Test
    fun `receive directory content disconnected`() =
        runBlocking {
            MockNetworkHandler.disconnect()
            try {
                val details = MockNetworkDirectoryDetails("Photos", 1)
                val result = useCase(details)
                fail("Should've thrown")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.NOT_CONNECTED.message, e.message)
            }
        }
}
