package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.DirectoryContent
import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.FolderDirectoryContent
import com.kevinschildhorn.fotopresenter.data.ImageDirectoryContent
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory

class DirectoryRepository(
    private val dataSource: DirectoryDataSource,
) {
    suspend fun getDirectoryContents(path: String): DirectoryContents {
        val folderDirectories: List<NetworkDirectory> = dataSource.getFolderDirectories(path)
        val imageDirectories: List<NetworkDirectory> = dataSource.getImageDirectories(path)

        return DirectoryContents(
            folders = folderDirectories.map { FolderDirectoryContent(it) },
            images = imageDirectories.map { ImageDirectoryContent(it) },
        )
    }
}