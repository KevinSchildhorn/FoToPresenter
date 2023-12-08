package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails

class DirectoryRepository(
    private val dataSource: DirectoryDataSource,
) {
    suspend fun getDirectoryContents(path: String): DirectoryContents {
        val folderDirectories: List<NetworkDirectoryDetails> = dataSource.getFolderDirectories(path)
        val imageDirectories: List<NetworkDirectoryDetails> = dataSource.getImageDirectories(path)

        return DirectoryContents(
            folders = folderDirectories.map { FolderDirectory(it) },
            images = imageDirectories.map { ImageDirectory(it) },
        )
    }
}
