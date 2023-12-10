package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

interface Directory {
    val details: NetworkDirectoryDetails

    val name: String
        get() = details.name

    val id: Int
        get() = details.id
}

data class FolderDirectory(
    override val details: NetworkDirectoryDetails,
) : Directory {
    override fun toString(): String =
        "(F:${details.fullPath}:${details.id})"
}

data class ImageDirectory(
    override val details: NetworkDirectoryDetails,
    val image: SharedImage? = null,
) : Directory {
    override fun toString(): String =
        "(I:${details.fullPath}:${details.id})"
}

data class DirectoryContents(
    val folders: List<FolderDirectory> = emptyList(),
    val images: List<ImageDirectory> = emptyList(),
) {
    val allDirectories: List<Directory>
        get() = folders + images

    override fun toString(): String {
        return """
            DirectoryContents:
            Folders: ${folders.count()}
                ${folders.map { it.toString() }.joinToString(", ")}
            Images: ${images.count()}
                ${images.map { it.toString() }.joinToString(", ")}
            """
    }
}
