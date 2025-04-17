package com.kevinschildhorn.fotopresenter.data.repositories

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlinx.coroutines.CancellationException

class DirectoryRepository(
    private val directoryDataSource: DirectoryDataSource,
    private val logger: Logger,
) {
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend fun changeDirectory(path: Path): Path =
        try {
            logger.d { "Changing Directory to path '$path'" }
            directoryDataSource.changeDirectory(path)
        } catch (e: Exception) {
            logger.e(e) { "Error Changing Directory" }
            throw NetworkHandlerException(e.message ?: "")
        }

    suspend fun getDirectoryContents(path: Path): DirectoryContents {
        val folderDirectories: List<NetworkDirectoryDetails> =
            directoryDataSource.getFolderDirectories(path)
        val imageDirectories: List<NetworkDirectoryDetails> =
            directoryDataSource.getImageDirectories(path)

        return DirectoryContents(
            folders = folderDirectories.map { FolderDirectory(it) },
            images =
                imageDirectories.map { networkDetails ->
                    ImageDirectory(
                        networkDetails,
                        // metaData.files.find { networkDetails.fullPath == it.filePath }
                        metaData = null,
                    )
                },
        )
    }

    suspend fun getDirectoryContentsRecursive(path: Path): DirectoryContents {
        val currentContents = getDirectoryContents(path)

        // Recursively get contents of all subfolders
        val subFolderContents =
            currentContents.folders.flatMap { folder ->
                try {
                    val subPath = folder.details.fullPath
                    if (subPath.fileName.endsWith("..")) {
                        emptyList()
                    } else {
                        val subContents = getDirectoryContentsRecursive(subPath)
                        subContents.images
                    }
                } catch (e: Exception) {
                    logger.e(e) { "Error getting recursive contents for folder ${folder.details.fullPath}" }
                    emptyList()
                }
            }

        // Combine current directory images with all subfolder images
        return DirectoryContents(
            folders = currentContents.folders,
            images = currentContents.images + subFolderContents,
        )
    }
}
