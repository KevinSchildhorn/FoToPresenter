package com.kevinschildhorn.fotopresenter.data.datasources

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
