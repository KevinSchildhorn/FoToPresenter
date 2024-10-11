package com.kevinschildhorn.fotopresenter.data

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DirectoryEtc(
    private val imageRepository: ImageRepository,
    private val directoryRepository: DirectoryRepository,
    private val logger: Logger,
) {

    private val _directoryContentsFlow = MutableStateFlow<DirectoryContents>(DirectoryContents())
    private val _imageDirectoryList = MutableStateFlow<List<ImageDirectory>>(emptyList())

    val directoryFlow: Flow<DirectoryContents> =
        combine(_directoryContentsFlow, _imageDirectoryList) { directory, images ->
            directory.copy(images = images)
        }

    fun refreshDirectories(path: String, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.Default) {
            // Retrieve File Data
            var directoryContents = directoryRepository.getDirectoryContents(path)
            _directoryContentsFlow.emit(directoryContents)
            _imageDirectoryList.emit(directoryContents.images)

            // For each image, get the Byte Array
            val images = directoryContents
                .images
                //.take(10)
                .toMutableList()

            images.chunked(10).forEach { imageChunk ->
                logger.i("Starting Jobs!")
                runBlocking {
                    imageChunk.forEachIndexed { index, image ->
                        coroutineScope.launch {
                            processItemAsync(index, image)
                        }
                    }
                }
                logger.i("Joined!")
            }

            logger.i { "Finished!" }
        }
    }

    suspend fun processItemAsync(
        index: Int,
        item: ImageDirectory,
    ) {
        var mutableItemDirectory = item
        val itemWithBytes = withContext(Dispatchers.IO) {
            val sharedImage = imageRepository.getImage(item.details)
            mutableItemDirectory.copy(image = sharedImage)
        }
        updateImageList(index, itemWithBytes)
        val itemWithBitmap = withContext(Dispatchers.IO) {
            val bitmap = itemWithBytes.image?.getImageBitmap(256)
            itemWithBytes.copy(imageBitmap = bitmap)
        }
        updateImageList(index, itemWithBitmap)
        item.image?.close()
    }

    private fun updateImageList(index: Int, item: ImageDirectory) {
        val items = _imageDirectoryList.value.toMutableList()
        items[index] = item
        _imageDirectoryList.update { items }
    }
}