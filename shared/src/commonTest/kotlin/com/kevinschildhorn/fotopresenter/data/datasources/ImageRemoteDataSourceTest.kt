package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.fail

/**
Testing [ImageRemoteDataSource]
 **/
class ImageRemoteDataSourceTest {
    private val networkHandler: MockNetworkHandler = MockNetworkHandler
    private val dataSource = ImageRemoteDataSource(networkHandler)

    @BeforeTest
    fun startTest() = runBlocking {
        networkHandler.connectSuccessfully()
    }

    @AfterTest
    fun tearDown() =
        runBlocking {
            networkHandler.disconnect()
        }

    @Test
    fun `get Image Success`() = runBlocking {
        val networkDirectory =
            MockNetworkDirectory(
                fullPath = "Photos/Peeng.png",
                id = 1,
            )
        try {
            val image = dataSource.getImage(networkDirectory)
        } catch (e:Exception){
            assertEquals("Success", e.message)
        }
    }

    @Test
    fun `get Image Failure`() = runBlocking {
        val networkDirectory =
            MockNetworkDirectory(
                fullPath = "Photos/nonExistant.png",
                id = 1,
            )
        val image = dataSource.getImage(networkDirectory)
        assertNull(image)
    }

    @Test
    fun `get Image Disconnected`() = runBlocking {
        networkHandler.disconnect()
        try {
            val image = dataSource.getImage(MockNetworkDirectory("", 1))
            fail("Should have thrown exception")
        } catch (e: NetworkHandlerException) {
            assertEquals(e.message, NetworkHandlerError.NOT_CONNECTED.message)
        }
    }
}
