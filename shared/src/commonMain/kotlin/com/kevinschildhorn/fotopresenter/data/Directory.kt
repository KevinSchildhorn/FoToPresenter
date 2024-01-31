package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.SortingType
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
    override fun toString(): String = "(F:${details.fullPath}:${details.id})"

    val isValid: Boolean
        get() = name != ".." &&
                name.isNotEmpty() &&
                name.isNotBlank()
}

data class ImageDirectory(
    override val details: NetworkDirectoryDetails,
    val metaData: MetadataFileDetails?,
    val image: SharedImage? = null,
) : Directory {
    override fun toString(): String = "(I:${details.fullPath}:${details.id})"
}

data class DirectoryContents(
    val folders: List<FolderDirectory> = emptyList(),
    val images: List<ImageDirectory> = emptyList(),
) {
    val allDirectories: List<Directory>
        get() = folders + images

    fun sorted(sortingType: SortingType): DirectoryContents {
        return DirectoryContents(
            folders = folders.sorted(sortingType) as List<FolderDirectory>,
            images = images.sorted(sortingType) as List<ImageDirectory>,
        )
    }

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

fun List<Directory>.sorted(sortingType: SortingType): List<Directory> =
    when (sortingType) {
        SortingType.NAME_ASC -> this.sortedBy { it.name }
        SortingType.NAME_DESC -> this.sortedByDescending { it.name }
        SortingType.TIME_ASC -> this.sortedBy { it.details.dateMillis }
        SortingType.TIME_DESC -> this.sortedByDescending { it.details.dateMillis }
    }
