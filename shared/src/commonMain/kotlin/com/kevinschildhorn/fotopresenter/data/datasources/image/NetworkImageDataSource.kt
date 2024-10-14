package com.kevinschildhorn.fotopresenter.data.datasources.image

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

class NetworkImageDataSource(private val networkHandler: NetworkHandler) {
    suspend fun getImage(directory: NetworkDirectoryDetails): SharedImage? =
        if (networkHandler.isConnected) {
            networkHandler.openImage(path = directory.fullPath)
        } else null
}