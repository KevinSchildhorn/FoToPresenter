package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails

class DirectoryRepository(
    private val directoryDataSource: DirectoryDataSource,
    private val metadataDataSource: ImageMetadataDataSource,
) {
    suspend fun getDirectoryContents(path: Path): DirectoryContents {
        val folderDirectories: List<NetworkDirectoryDetails> =
            directoryDataSource.getFolderDirectories(path)
        val imageDirectories: List<NetworkDirectoryDetails> =
            directoryDataSource.getImageDirectories(path)

        // val metaData = metadataDataSource.importMetaData()

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
}
