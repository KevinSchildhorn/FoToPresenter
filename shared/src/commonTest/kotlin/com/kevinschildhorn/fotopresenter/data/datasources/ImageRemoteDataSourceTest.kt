package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
Testing [ImageRemoteDataSource]
 **/
class ImageRemoteDataSourceTest {
    private val networkHandler: MockNetworkHandler = MockNetworkHandler
    private val dataSource = ImageRemoteDataSource(networkHandler)

    @BeforeTest
    fun startTest() =
        runBlocking {
            networkHandler.connectSuccessfully()
        }

    @AfterTest
    fun tearDown() =
        runBlocking {
            networkHandler.disconnect()
        }

    @Test
    fun `get Image Failure`() =
        runBlocking {
            networkHandler.connectSuccessfully()
            val networkDirectory =
                MockNetworkDirectory(
                    fullPath = "Photos/Peeng.png",
                    id = 1,
                )
            val success = dataSource.getImage(networkDirectory)
        }

    @Test
    fun `get Image Disconnected`() =
        runBlocking {
            networkHandler.disconnect()
        /*
        try {
            dataSource.getImage("")
            fail("Should have thrown exception")
        } catch (e: NetworkHandlerException) {
            assertEquals(e.message, NetworkHandlerError.NOT_CONNECTED.message)
        }*/
        }
}
