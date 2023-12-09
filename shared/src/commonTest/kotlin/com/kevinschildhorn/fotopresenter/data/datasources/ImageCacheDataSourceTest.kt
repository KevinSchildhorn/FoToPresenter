package com.kevinschildhorn.fotopresenter.data.datasources

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.MockSharedCache
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/* TODO: Requires mocking
/**
Testing [ImageCacheDataSource]
 **/
class ImageCacheDataSourceTest {
    private val cache = MockSharedCache()
    private val dataSource = ImageCacheDataSource(cache)

    @Test
    fun `save Image`() =
        runBlocking {
            val directoryDetails = MockNetworkDirectoryDetails(
                fullPath = "Photos/sample.png",
                id = 1,
            )
            val bitmap = mockk<ImageBitmap>()
            assertTrue(cache.contents.isEmpty())
            dataSource.saveImage(directoryDetails, bitmap)
            assertFalse(cache.contents.isEmpty())

            val result = dataSource.getImage(directoryDetails)
            assertEquals(bitmap, result)
        }
}*/