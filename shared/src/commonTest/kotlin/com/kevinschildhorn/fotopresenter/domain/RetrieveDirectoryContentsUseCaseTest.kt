package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
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
import kotlin.test.assertTrue
import kotlin.test.fail

/**
Testing [RetrieveDirectoryContentsUseCase]
 **/
class RetrieveDirectoryContentsUseCaseTest : KoinTest {
    private val useCase: RetrieveDirectoryContentsUseCase by inject()

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
            val result = useCase("")
            assertTrue(result.images.first().directory.isAnImage)
            assertTrue(result.folders.first().directory.isDirectory)
            assertEquals(1, result.folders.count())
            assertEquals(2, result.images.count())
            assertEquals(3, result.allDirectories.count())
        }

    @Test
    fun `receive directory content only directories`() =
        runBlocking {
            val result = useCase("Directories")
            assertEquals(2, result.folders.count())
            assertEquals(0, result.images.count())
            assertEquals(2, result.allDirectories.count())
        }

    @Test
    fun `receive directory content failure`() =
        runBlocking {
            val result = useCase("nonExistant")
            assertEquals(0, result.folders.count())
            assertEquals(0, result.images.count())
            assertEquals(0, result.allDirectories.count())
        }

    @Test
    fun `receive directory content disconnected`() =
        runBlocking {
            MockNetworkHandler.disconnect()
            try {
                val result = useCase("Photos")
                fail("Should've thrown")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.NOT_CONNECTED.message, e.message)
            }
        }
}
