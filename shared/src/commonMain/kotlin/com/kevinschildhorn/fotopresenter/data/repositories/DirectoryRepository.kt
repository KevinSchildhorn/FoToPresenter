package com.kevinschildhorn.fotopresenter.data.repositories

import com.kevinschildhorn.fotopresenter.data.DirectoryContents
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource

class DirectoryRepository(
    private val dataSource: DirectoryDataSource,
) {
    suspend fun getDirectoryContents(path:String): DirectoryContents {
        val folderDirectories = dataSource.getFolderDirectories(path)
        val imageDirectories = dataSource.getImageDirectories(path)

        return DirectoryContents(
            folders = folderDirectories,
            images = imageDirectories
        )
    }
}