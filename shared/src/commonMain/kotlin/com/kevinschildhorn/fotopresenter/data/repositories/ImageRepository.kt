package com.kevinschildhorn.fotopresenter.data.repositories

import androidx.compose.ui.graphics.ImageBitmap
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.datasources.ImageRemoteDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory

class ImageRepository(
    private val remoteDataSource: ImageRemoteDataSource,
) {
    suspend fun getImage(directory:NetworkDirectory): ImageBitmap? =
        remoteDataSource.getImage(directory)
    }
}