package com.kevinschildhorn.fotopresenter.data.datasources

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerError
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlin.coroutines.cancellation.CancellationException

/**
Fetches Directory info from a NAS
 **/
class DirectoryDataSource(
    private val networkHandler: NetworkHandler,
    private val logger: Logger,
) {
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun changeDirectory(directoryName: String): String {
        logger.i { "Changing directory to $directoryName" }
        logger.i { "Is network Connected? ${networkHandler.isConnected}" }
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)

        logger.i { "Does the directory exist?" }
        val exists = networkHandler.folderExists(directoryName)
        logger.i { "Does the directory exist? $exists" }

        logger.i { "Opening the directory..." }
        networkHandler.openDirectory(directoryName)?.let { return it }
        throw NetworkHandlerException(NetworkHandlerError.DIRECTORY_NOT_FOUND)
    }

    suspend fun getFolderDirectories(path: String): List<NetworkDirectoryDetails> {
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler.getDirectoryContents(path).filter {
            it.fileName != "."
        }.filter { it.isDirectory }
    }

    suspend fun getImageDirectories(path: String): List<NetworkDirectoryDetails> {
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler.getDirectoryContents(path).filter {
            it.fileName != "."
        }.filter { it.isAnImage }
    }
}
