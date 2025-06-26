package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.ui.SortingType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

/*
 * The contents of a Directory on your NAS File System.
 * Contains folders and images each of which has DirectoryDetails
 */
data class DirectoryContents(
    val currentDirectory: FolderDirectory? = null,
    val folders: List<FolderDirectory> = emptyList(),
    val images: List<ImageDirectory> = emptyList(),
) {
    val allDirectories: List<Directory>
        get() = folders + images

    fun sorted(sortingType: SortingType): DirectoryContents =
        DirectoryContents(
            currentDirectory = currentDirectory,
            folders = folders.sorted(sortingType),
            images = images.sorted(sortingType),
        )

    fun filteredByName(string: String): DirectoryContents =
        DirectoryContents(
            currentDirectory = currentDirectory,
            folders = folders.filteredByName(string),
            images = images.filteredByName(string),
        )

    fun filteredByTags(
        tags: List<String>,
        allTags: Boolean,
    ): DirectoryContents =
        DirectoryContents(
            currentDirectory = currentDirectory,
            folders = emptyList(),
            images = images.filteredByTags(tags, allTags),
        )

    fun filteredByDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ): DirectoryContents =
        DirectoryContents(
            currentDirectory = currentDirectory,
            folders = emptyList(),
            images =
                images.filter { directory ->
                    val startMillis =
                        startDate
                            .atStartOfDayIn(TimeZone.currentSystemDefault())
                            .toEpochMilliseconds()
                    val endMillis =
                        endDate
                            .atStartOfDayIn(TimeZone.currentSystemDefault())
                            .toEpochMilliseconds()

                    startMillis < directory.details.dateMillis &&
                        directory.details.dateMillis < endMillis
                },
        )

    override fun toString(): String =
        """
        DirectoryContents:
        Current Directory: ${currentDirectory?.name}
        Folders: ${folders.count()}
            ${folders.map { it.toString() }.joinToString(", ")}
        Images: ${images.count()}
            ${images.map { it.toString() }.joinToString(", ")}
        """
}
