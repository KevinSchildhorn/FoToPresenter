package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlin.coroutines.cancellation.CancellationException

/**
Fetches Photos from a NAS
 **/
class ImageRemoteDataSource(private val networkHandler: NetworkHandler) {

    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun getImage(directory: NetworkDirectory): SharedImage? {
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler.openImage(directory.fullPath)
    }
}