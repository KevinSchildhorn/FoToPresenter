package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.SharedImage

interface DirectoryContent {
    val directory: NetworkDirectory
}

data class FolderDirectoryContent(
    override val directory: NetworkDirectory,
) : DirectoryContent

data class ImageDirectoryContent(
    override val directory: NetworkDirectory,
    val image: SharedImage? = null,
) : DirectoryContent

data class DirectoryContents(
    val folders: List<DirectoryContent> = emptyList(),
    val images: List<DirectoryContent> = emptyList(),
) {
    val allDirectories: List<DirectoryContent>
        get() = folders + images
}
