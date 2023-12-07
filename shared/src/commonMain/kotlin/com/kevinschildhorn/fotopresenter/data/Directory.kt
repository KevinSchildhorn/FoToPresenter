package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.SharedImage

interface Directory {
    val details: NetworkDirectoryDetails

    val name: String
        get() = details.name

    val id: Int
        get() = details.id
}

data class FolderDirectory(
    override val details: NetworkDirectoryDetails,
) : Directory

data class ImageDirectory(
    override val details: NetworkDirectoryDetails,
    val image: SharedImage? = null,
) : Directory

data class DirectoryContents(
    val folders: List<Directory> = emptyList(),
    val images: List<Directory> = emptyList(),
) {
    val allDirectories: List<Directory>
        get() = folders + images
}
