package com.kevinschildhorn.fotopresenter.data.repositories

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
    fun startTest() = runBlocking {
        startKoin {
            modules(testingModule())
        }
        networkHandler.connectSuccessfully()
    }

    @AfterTest
    fun tearDown() = runBlocking {
        stopKoin()
        networkHandler.disconnect()
    }


    @Test
    fun `retrieve Directory Contents Success`() = runBlocking {
        val result = repository.getDirectoryContents("")
        assertEquals(1, result.folders.count())
        assertEquals(2, result.images.count())
    }

    @Test
    fun `retrieve Directory Contents Disconnected`() = runBlocking {
        networkHandler.disconnect()
        try {
            val result = repository.getDirectoryContents("")
            fail("Should Throw Exception")
        } catch (e: NetworkHandlerException) {
            assertEquals(e.message, NetworkHandlerError.NOT_CONNECTED.message)
        }
    }
}