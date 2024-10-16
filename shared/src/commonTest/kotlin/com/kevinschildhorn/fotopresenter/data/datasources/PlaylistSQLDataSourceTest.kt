package com.kevinschildhorn.fotopresenter.data.datasources

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.kevinschildhorn.fotopresenter.PlaylistDatabase
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.network.DefaultNetworkDirectoryDetails
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.fail

/**
Testing [PlaylistSQLDataSource]
 **/
class PlaylistSQLDataSourceTest {
    private val imageDirectory =
        ImageDirectory(
            details =
                DefaultNetworkDirectoryDetails(
                    fullPath = "Image1.png",
                    id = 1,
                ),
            metaData = null,
        )
    private val imageDirectoryList: List<ImageDirectory> =
        listOf(
            imageDirectory,
            ImageDirectory(
                details =
                    DefaultNetworkDirectoryDetails(
                        fullPath = "Photos/Image2.png",
                        id = 2,
                    ),
                metaData = null,
            ),
            ImageDirectory(
                details =
                    DefaultNetworkDirectoryDetails(
                        fullPath = "Image3.png",
                        id = 3,
                    ),
                metaData = null,
            ),
        )

    @Test
    fun `Create Playlist Success`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlist = dataSource.createPlaylist("Playlist1")
        assertEquals("Playlist1", playlist?.name)
        assertEquals(1, playlist?.id)
    }

    @Test
    fun `Create Playlist Large`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlist = dataSource.createPlaylist("Playlist1", imageDirectoryList)
        assertEquals("Playlist1", playlist?.name)
        assertEquals(1, playlist?.id)
        imageDirectoryList.forEach {
            val image = dataSource.getPlaylistImage(playlist?.id!!, it.details.fullPath)
            assertNotNull(image)
        }
    }

    @Test
    fun `Create Playlist Failure`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlist1 = dataSource.createPlaylist("Playlist1")
        val playlist2 = dataSource.createPlaylist("Playlist1")
        assertNotNull(playlist1)
        assertNull(playlist2)
    }

    @Test
    fun `Insert Playlist Image Success`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        dataSource.createPlaylist("Playlist1")?.let { playlist ->
            val image = dataSource.insertPlaylistImage(playlist.id, imageDirectory)
            assertEquals(playlist.id, image?.playlistId)
            assertEquals(imageDirectory.details.fullPath, image?.directoryPath)
        } ?: run {
            fail("Didn't Create Playlist")
        }
    }

    @Test
    fun `Insert Playlist Image Failure`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        dataSource.createPlaylist("Playlist1")?.let { playlist ->
            val image1 = dataSource.insertPlaylistImage(playlist.id, imageDirectory)
            val image2 = dataSource.insertPlaylistImage(playlist.id, imageDirectory)
            assertNotNull(image1)
            assertNull(image2)
        } ?: run {
            fail("Didn't Create Playlist")
        }
    }

    @Test
    fun `Get Playlist by Name Success`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlistName = "Playlist1"
        val playlist = dataSource.createPlaylist(playlistName)
        val selectedPlaylist = dataSource.getPlaylistByName(playlistName)
        assertEquals(playlist?.name, selectedPlaylist?.name)
        assertEquals(playlist?.id, selectedPlaylist?.id)
    }

    @Test
    fun `Select Playlist by Name Failure`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val selectedPlaylist = dataSource.getPlaylistByName("NonExistant")
        assertNull(selectedPlaylist)
    }

    @Test
    fun `Get Playlist by Id Success`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlistName = "Playlist1"
        val playlist = dataSource.createPlaylist(playlistName)
        val selectedPlaylist = dataSource.getPlaylistById(playlist?.id ?: -1)
        assertEquals(playlist?.name, selectedPlaylist?.name)
        assertEquals(playlist?.id, selectedPlaylist?.id)
    }

    @Test
    fun `Select Playlist by Id Failure`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val selectedPlaylist = dataSource.getPlaylistById(-1)
        assertNull(selectedPlaylist)
    }

    @Test
    fun `Get Playlist Image Success`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        dataSource.createPlaylist("Playlist1")?.let { playlist ->
            val image1 = dataSource.insertPlaylistImage(playlist.id, imageDirectory)
            val image2 = dataSource.getPlaylistImage(playlist.id, imageDirectory.details.fullPath)
            assertNotNull(image1)
            assertNotNull(image2)
            assertEquals(image1.playlistId, image2.playlistId)
            assertEquals(image1.id, image2.id)
            assertEquals(image1.directoryPath, image2.directoryPath)
        } ?: run {
            fail("Didn't Create Playlist")
        }
    }

    @Test
    fun `Get Playlist Image Failure`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val image = dataSource.getPlaylistImage(0, imageDirectory.details.fullPath)
        assertNull(image)
    }

    @Test
    fun `Delete Playlist Success`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val image = dataSource.getPlaylistImage(0, imageDirectory.details.fullPath)
        assertNull(image)
    }

    @Test
    fun `Delete Playlist Failure`() {
        val playlistName = "Playlist1"
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlist = dataSource.createPlaylist(playlistName, imageDirectoryList)
        assertNotNull(playlist)

        val newPlaylist = dataSource.getPlaylistByName(playlistName)
        assertNotNull(newPlaylist)

        val image = dataSource.getPlaylistImage(playlist.id, imageDirectory.details.fullPath)
        assertNotNull(image)

        val result = dataSource.deletePlaylist(playlist.id)
        assertTrue(result)

        val deletedPlaylist = dataSource.getPlaylistByName(playlistName)
        assertNull(deletedPlaylist)
        val deletedImage = dataSource.getPlaylistImage(playlist.id, imageDirectory.details.fullPath)
        assertNull(deletedImage)
    }

    @Test
    fun `Select All Playlists Success`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlist = dataSource.createPlaylist("Playlist1", imageDirectoryList)
        val playlists = dataSource.getAllPlaylists()
        assertEquals(1, playlists.count())
    }

    @Test
    fun `Select All Playlists Failure`() {
        val dataSource = PlaylistSQLDataSource(createInMemorySqlDriver())
        val playlists = dataSource.getAllPlaylists()
        assertEquals(0, playlists.count())
    }

    private fun createInMemorySqlDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        PlaylistDatabase.Schema.create(driver)
        return driver
    }
}
