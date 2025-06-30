package com.kevinschildhorn.fotopresenter.data.datasources

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.Path
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
    private val logger: Logger?,
) {
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun changeDirectory(directoryName: Path): Path {
        logger?.v { "Changing directory to $directoryName" }
        logger?.v { "Is network Connected? ${networkHandler.isConnected}" }
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)

        logger?.v { "Does the directory exist?" }
        // val exists = networkHandler.folderExists(directoryName)
        // logger?.i { "Does the directory exist? $exists" }

        logger?.v { "Opening the directory..." }
        networkHandler.openDirectory(directoryName)?.let { return it }
        throw NetworkHandlerException(NetworkHandlerError.DIRECTORY_NOT_FOUND)
    }

    suspend fun getDirectoryForPath(path: Path): NetworkDirectoryDetails? {
        logger?.v { "Getting Directory for path '$path'" }
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler.getDirectoryDetails(path)
    }

    suspend fun getFolderDirectories(path: Path): List<NetworkDirectoryDetails> {
        logger?.v { "Getting Folder Directories at path '$path'" }
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler
            .getDirectoryContents(path)
            .filter {
                it.fileName != "."
            }.filter { it.isDirectory }
    }

    suspend fun getImageDirectories(path: Path): List<NetworkDirectoryDetails> {
        logger?.v { "Getting Image Directories at path '$path'" }
        if (!networkHandler.isConnected) throw NetworkHandlerException(NetworkHandlerError.NOT_CONNECTED)
        return networkHandler
            .getDirectoryContents(path)
            .filter {
                it.fileName != "."
            }.filter { it.isAnImage }
    }
}
