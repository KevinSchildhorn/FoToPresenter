package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.PlaylistDetails
import com.kevinschildhorn.fotopresenter.data.PlaylistItem
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
Testing [PlaylistFileDataSource]
 **/
class PlaylistFileDataSourceTest {
    private val networkHandler: MockNetworkHandler = MockNetworkHandler

    private val newPlaylist =
        PlaylistDetails(
            id = 2,
            name = "NewPlaylist",
            items =
                listOf(
                    PlaylistItem(
                        id = 1,
                        playlistId = 2,
                        directoryPath = Path("Photos/SubPhotos/Peeng3.png"),
                        directoryId = 2,
                    ),
                    PlaylistItem(
                        id = 2,
                        playlistId = 2,
                        directoryPath = Path("Photos/SubPhotos/Jaypeg3.jpg"),
                        directoryId = 3,
                    ),
                ),
        )

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
    fun `Import_Playlist`() =
        runBlocking {
            val dataSource = PlaylistFileDataSource(null, networkHandler)
            val playlists = dataSource.importPlaylists()

            val existingPlaylist = playlists.firstOrNull()
            assertNotNull(existingPlaylist)
            assertEquals("Existing", existingPlaylist.name)
            assertEquals(2, existingPlaylist.items.count())
        }

    @Test
    fun `Export_Playlist`() =
        runBlocking {
            val dataSource = PlaylistFileDataSource(null, networkHandler)
            var playlists = dataSource.importPlaylists().toMutableList()
            assertEquals(1, playlists.count())

            val result = dataSource.exportPlaylist(newPlaylist)
            assertTrue(result)

            playlists = dataSource.importPlaylists().toMutableList()

            val searchedPlaylist = playlists.find { it.name == "NewPlaylist" }
            assertNotNull(searchedPlaylist)
            print("finished")
        }

    @Test
    fun `Delete_Playlist`() =
        runBlocking {
            val dataSource = PlaylistFileDataSource(null, networkHandler)
            dataSource.exportPlaylist(newPlaylist)
            var playlists = dataSource.importPlaylists()
            val searchedPlaylist = playlists.find { it.name == "NewPlaylist" }
            assertNotNull(searchedPlaylist)
            dataSource.deletePlaylist(searchedPlaylist)

            playlists = dataSource.importPlaylists()
            assertEquals(1, playlists.count())
        }
}
