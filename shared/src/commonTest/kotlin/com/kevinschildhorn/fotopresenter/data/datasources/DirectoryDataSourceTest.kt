package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.Path
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
    private val dataSource = DirectoryDataSource(networkHandler, null)

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
    fun `change_Directory_Success`() =
        runBlocking {
            val success = dataSource.changeDirectory(Path("Photos"))
            assertEquals(expected = Path("Photos"), actual = success)
        }

    @Test
    fun `change_Directory_Failure`() =
        runBlocking {
            networkHandler.connectSuccessfully()
            try {
                dataSource.changeDirectory(Path("NonExistant"))
                fail("Should've thrown exception")
            } catch (e: NetworkHandlerException) {
                assertEquals(
                    expected = NetworkHandlerError.DIRECTORY_NOT_FOUND.message,
                    actual = e.message,
                )
            }
        }

    @Test
    fun `change_Directory_Disconnected`() =
        runBlocking {
            networkHandler.disconnect()
            try {
                dataSource.changeDirectory(Path(""))
                fail("Should have thrown exception")
            } catch (e: NetworkHandlerException) {
                assertEquals(
                    expected = NetworkHandlerError.NOT_CONNECTED.message,
                    actual = e.message,
                )
            }
        }

    //endregion

    //region Directory Contents

    @Test
    fun `get_Directory_Contents_Success`() =
        runBlocking {
            val directories = dataSource.getFolderDirectories(Path("Directories"))
            assertEquals(expected = 2, actual = directories.count())
        }

    @Test
    fun `get_Directory_Contents_Mixed`() =
        runBlocking {
            val directories = dataSource.getFolderDirectories(Path(""))
            assertEquals(expected = 2, actual = directories.count())
        }

    @Test
    fun `get_Directory_Contents_Empty`() =
        runBlocking {
            val directories = dataSource.getFolderDirectories(Path("Photos"))
            assertEquals(expected = 1, actual = directories.count())
        }

    //endregion

    //region Image Directory Contents

    @Test
    fun `get_Directory_Images_Success`() =
        runBlocking {
            val directories = dataSource.getImageDirectories(Path("Photos"))
            assertEquals(expected = 2, actual = directories.count())
        }

    @Test
    fun `get_Directory_Images_Mixed`() =
        runBlocking {
            val directories = dataSource.getImageDirectories(Path(""))
            assertEquals(expected = 2, actual = directories.count())
        }

    @Test
    fun `get_Directory_Images_Empty`() =
        runBlocking {
            val directories = dataSource.getImageDirectories(Path("Directories"))
            assertEquals(expected = 0, actual = directories.count())
        }

    //endregion
}
