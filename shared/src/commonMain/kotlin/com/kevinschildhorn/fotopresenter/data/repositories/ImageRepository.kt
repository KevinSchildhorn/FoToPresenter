package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.datasources.ImageRemoteDataSource

class ImageRepository(
    private val remoteDataSource: ImageRemoteDataSource,
) {
    suspend fun getPhotosAtDirectory(): List<DirectoryContents> {
        //remoteDataSource.getImage()
        return emptyList()
    }
}