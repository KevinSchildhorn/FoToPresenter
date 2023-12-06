package com.kevinschildhorn.fotopresenter.data.datasources

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlin.coroutines.cancellation.CancellationException

/**
Fetches Directory info from a NAS
 **/
class DirectoryDataSource(private val networkHandler: NetworkHandler) {
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun changeDirectory(directoryName: String): String {
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)

        networkHandler.openDirectory(directoryName)?.let { return it }
        throw NetworkHandlerException(NetworkHandlerError.DIRECTORY_NOT_FOUND)
    }

    suspend fun getFolderDirectories(path: String): List<NetworkDirectory> {
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler.getDirectoryContents(path).filter {
            it.fileName != "."
        }.filter { it.isDirectory }
    }

    suspend fun getImageDirectories(path: String): List<NetworkDirectory> {
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler.getDirectoryContents(path).filter {
            it.fileName != "."
        }.filter { it.isAnImage }
    }
}
