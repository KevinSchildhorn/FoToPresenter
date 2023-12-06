package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.datasources.ImageRemoteDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.SharedImage

class ImageRepository(
    private val remoteDataSource: ImageRemoteDataSource,
) {
    suspend fun getImage(directory: NetworkDirectory): SharedImage? =
        remoteDataSource.getImage(directory)

}