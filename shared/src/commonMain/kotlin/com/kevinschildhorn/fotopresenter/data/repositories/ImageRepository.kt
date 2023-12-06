package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.datasources.ImageRemoteDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory

class ImageRepository(
    private val remoteDataSource: ImageRemoteDataSource,
) {
    suspend fun getImage(directory: NetworkDirectory): SharedImage? =
        remoteDataSource.getImage(directory)

}