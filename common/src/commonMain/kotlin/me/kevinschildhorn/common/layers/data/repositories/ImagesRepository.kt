package me.kevinschildhorn.common.layers.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.kevinschildhorn.common.layers.data.datasources.LocalImagesDataSource
import me.kevinschildhorn.common.layers.data.datasources.RemoteImagesDataSource
import me.kevinschildhorn.common.layers.data.datasources.interfaces.Images
import org.koin.core.component.KoinComponent

/**
Fetches [Images] from Data Sources
 **/
class ImagesRepository(
    private val localDataSource: LocalImagesDataSource,
    private val remoteDataSource: RemoteImagesDataSource
) : KoinComponent {

    private val _images: MutableStateFlow<Images> = MutableStateFlow(emptyList())
    val images: Flow<Images> = _images

    suspend fun fetchImages() {
        val images = remoteDataSource.fetchImages()
        localDataSource.updateImages(images)

        _images.value = localDataSource.fetchImages()
    }
}
