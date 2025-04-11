package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.testingModule
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

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
/*
    @Test
    fun `receive_directory_content_success`() =
        runBlocking {
            val result = useCase(Path(""))
            assertTrue(result.images.first().details.isAnImage)
            assertTrue(result.folders.first().details.isDirectory)
            assertEquals(2, result.folders.count())
            assertEquals(2, result.images.count())
            assertEquals(4, result.allDirectories.count())
        }

    @Test
    fun `receive_directory_content_only_directories`() =
        runBlocking {
            val result = useCase(Path("Directories"))
            assertEquals(2, result.folders.count())
            assertEquals(0, result.images.count())
            assertEquals(2, result.allDirectories.count())
        }

    @Test
    fun `receive_directory_content_failure`() =
        runBlocking {
            val result = useCase(Path("nonExistant"))
            assertEquals(0, result.folders.count())
            assertEquals(0, result.images.count())
            assertEquals(0, result.allDirectories.count())
        }

    @Test
    fun `receive_directory_content_disconnected`() =
        runBlocking {
            MockNetworkHandler.disconnect()
            try {
                val result = useCase(Path("Photos"))
                fail("Should've thrown")
            } catch (e: NetworkHandlerException) {
                assertEquals(NetworkHandlerError.NOT_CONNECTED.message, e.message)
            }
        }

    @Test
    fun `receive_directory_content_empty`() =
        runBlocking {
            val result = useCase(Path(""))
            assertEquals(0, result.folders.count())
            assertEquals(0, result.images.count())
            assertEquals(0, result.allDirectories.count())
        }
 */
}
