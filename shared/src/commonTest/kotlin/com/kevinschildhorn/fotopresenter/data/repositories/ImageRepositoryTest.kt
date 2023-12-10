package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.fail

/**
Testing [ImageRepository]
 **/
class ImageRepositoryTest : KoinTest {
    private val networkHandler: MockNetworkHandler = MockNetworkHandler
    private val repository: ImageRepository by inject()

    @BeforeTest
    fun startTest() =
        runBlocking {
            startKoin {
                modules(testingModule())
            }
            networkHandler.connectSuccessfully()
        }

    @AfterTest
    fun tearDown() =
        runBlocking {
            stopKoin()
            networkHandler.disconnect()
        }

    @Test
    fun `get Image Success`() =
        runBlocking {
            try {
                val result =
                    repository.getImage(
                        MockNetworkDirectoryDetails(
                            fullPath = "Photos/Success.png",
                            id = 1,
                        ),
                    )
            } catch (e: Exception) {
                assertEquals("Success", e.message)
            }
        }

    @Test
    fun `get Image Failure`() =
        runBlocking {
            val result =
                repository.getImage(
                    MockNetworkDirectoryDetails(
                        fullPath = "Photos/nonExistant.png",
                        id = 1,
                    ),
                )
            assertNull(result)
        }

    @Test
    fun `get Image Disconnected`() =
        runBlocking {
            networkHandler.disconnect()
            try {
                val result =
                    repository.getImage(
                        MockNetworkDirectoryDetails(
                            fullPath = "Photos/nonExistant.png",
                            id = 1,
                        ),
                    )
                fail("Should Throw Exception")
            } catch (e: NetworkHandlerException) {
                assertEquals(e.message, NetworkHandlerError.NOT_CONNECTED.message)
            }
        }
}
