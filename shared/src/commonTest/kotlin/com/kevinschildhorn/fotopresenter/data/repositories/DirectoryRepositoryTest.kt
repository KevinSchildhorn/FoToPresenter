package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.Path
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
import kotlin.test.fail

/**
Testing [DirectoryRepository]
 **/
class DirectoryRepositoryTest : KoinTest {
    private val networkHandler: MockNetworkHandler = MockNetworkHandler
    private val repository: DirectoryRepository by inject()

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
    fun `retrieve_Directory_Contents_Success`() =
        runBlocking {
            val result = repository.getDirectoryContents(Path.EMPTY)
            assertEquals(expected = 2, actual = result.folders.count())
            assertEquals(expected = 2, actual = result.images.count())
        }

    @Test
    fun `retrieve_Directory_Contents_Failure`() =
        runBlocking {
            val result = repository.getDirectoryContents(Path("nonExistant"))
            assertEquals(expected = 0, actual = result.folders.count())
            assertEquals(expected = 0, actual = result.images.count())
        }

    @Test
    fun `retrieve_Directory_Contents_Disconnected`() =
        runBlocking {
            networkHandler.disconnect()
            try {
                val result = repository.getDirectoryContents(Path(""))
                fail("Should Throw Exception")
            } catch (e: NetworkHandlerException) {
                assertEquals(
                    expected = NetworkHandlerError.NOT_CONNECTED.message,
                    actual = e.message
                )
            }
        }
}
