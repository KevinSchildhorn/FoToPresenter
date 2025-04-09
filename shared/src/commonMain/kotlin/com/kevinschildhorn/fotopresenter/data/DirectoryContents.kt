package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.ui.SortingType

/*
 * The contents of a Directory on your NAS File System.
 * Contains folders and images each of which has DirectoryDetails
 */
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

    fun filtered(string: String): DirectoryContents {
        return DirectoryContents(
            folders = folders.filtered(string) as List<FolderDirectory>,
            images = images.filtered(string) as List<ImageDirectory>,
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
