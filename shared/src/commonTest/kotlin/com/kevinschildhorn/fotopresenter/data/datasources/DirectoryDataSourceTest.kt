package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

/**
Testing [DirectoryDataSource]
 **/
class DirectoryDataSourceTest {
    private val networkHandler: MockNetworkHandler = MockNetworkHandler
    private val dataSource = DirectoryDataSource(networkHandler)

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

    //region Change Directory

    @Test
    fun `change Directory Success`() =
        runBlocking {
            val success = dataSource.changeDirectory("Photos")
            assertEquals("Photos", success)
        }

    @Test
    fun `change Directory Failure`() =
        runBlocking {
            networkHandler.connectSuccessfully()
            try {
                dataSource.changeDirectory("NonExistant")
                fail("Should've thrown exception")
            } catch (e: NetworkHandlerException) {
                assertEquals(e.message, NetworkHandlerError.DIRECTORY_NOT_FOUND.message)
            }
        }

    @Test
    fun `change Directory Disconnected`() =
        runBlocking {
            networkHandler.disconnect()
            try {
                dataSource.changeDirectory("")
                fail("Should have thrown exception")
            } catch (e: NetworkHandlerException) {
                assertEquals(e.message, NetworkHandlerError.NOT_CONNECTED.message)
            }
        }

    //endregion

    //region Directory Contents

    @Test
    fun `get Directory Contents Success`() =
        runBlocking {
            val directories = dataSource.getFolderDirectories("Directories")
            assertEquals(2, directories.count())
        }

    @Test
    fun `get Directory Contents Mixed`() =
        runBlocking {
            val directories = dataSource.getFolderDirectories("")
            assertEquals(2, directories.count())
        }

    @Test
    fun `get Directory Contents Empty`() =
        runBlocking {
            val directories = dataSource.getFolderDirectories("Photos")
            assertEquals(1, directories.count())
        }

    //endregion

    //region Image Directory Contents

    @Test
    fun `get Directory Images Success`() =
        runBlocking {
            val directories = dataSource.getImageDirectories("Photos")
            assertEquals(2, directories.count())
        }

    @Test
    fun `get Directory Images Mixed`() =
        runBlocking {
            val directories = dataSource.getImageDirectories("")
            assertEquals(2, directories.count())
        }

    @Test
    fun `get Directory Images Empty`() =
        runBlocking {
            val directories = dataSource.getImageDirectories("Directories")
            assertEquals(0, directories.count())
        }

    //endregion
}
