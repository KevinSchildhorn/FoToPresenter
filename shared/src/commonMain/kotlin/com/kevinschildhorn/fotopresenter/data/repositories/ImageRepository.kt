package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.datasources.ImageRemoteDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

class ImageRepository(
    private val remoteDataSource: ImageRemoteDataSource,
) {
    suspend fun getImage(directory: NetworkDirectoryDetails): SharedImage? = remoteDataSource.getImage(directory)
}
