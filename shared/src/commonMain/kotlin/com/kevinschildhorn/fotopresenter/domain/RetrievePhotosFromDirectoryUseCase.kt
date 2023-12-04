package com.kevinschildhorn.fotopresenter.domain

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler

/**
Retrieving Photos from current Directory
 **/
class RetrievePhotosFromDirectoryUseCase(
    private val client: NetworkHandler,
) {
    suspend operator fun invoke(): List<Pair<NetworkDirectory, ImageBitmap?>> {
        val results = client.getDirectoryContents()
        val photos = results.filter { it.isAnImage }
        return photos.map {
            Pair(it, client.openImage(it.fileName))
        }
    }
}