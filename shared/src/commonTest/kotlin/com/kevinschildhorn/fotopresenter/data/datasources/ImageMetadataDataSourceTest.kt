package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

/**
Testing [ImageMetadataDataSource]
 **/
class ImageMetadataDataSourceTest {
    private val networkHandler: MockNetworkHandler = MockNetworkHandler


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
    fun `Import Metadata`() = runBlocking {
        val dataSource = ImageMetadataDataSource(null, networkHandler)
        val metadata = dataSource.importMetaData()
        assertNotNull(metadata)
        assertEquals(1, metadata.files.count())
    }

    @Test
    fun `Export Playlist`() = runBlocking {
        val dataSource = ImageMetadataDataSource(null, networkHandler)
        var metadata = dataSource.importMetaData()
        assertNotNull(metadata)
        assertEquals(0, metadata.files.count())

        val newMetadataFileDetails = MetadataFileDetails(
            "MyPath.png",
            setOf("Tag1", "Wallpaper"),
        )
        val mutableFiles = metadata.files.toMutableList()
        mutableFiles.add(newMetadataFileDetails)
        metadata = metadata.copy(files = mutableFiles)

        val result = dataSource.exportMetadata(metadata)
        assertTrue(result)

        metadata = dataSource.importMetaData()

        assertNotNull(metadata)
        assertTrue(metadata.files.contains(newMetadataFileDetails))
    }
}