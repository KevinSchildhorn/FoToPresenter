package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.SortingType

interface Directory {
    val details: NetworkDirectoryDetails

    val name: String
        get() = details.name

    val id: Long
        get() = details.id
}

data class FolderDirectory(
    override val details: NetworkDirectoryDetails,
) : Directory {
    override fun toString(): String = "(F:${details.fullPath}:${details.id})"

    val isValid: Boolean
        get() =
            name != ".." &&
                name.isNotEmpty() &&
                name.isNotBlank()
}

data class ImageDirectory(
    override val details: NetworkDirectoryDetails,
    val metaData: MetadataFileDetails?,
) : Directory {
    override fun toString(): String = "(I:${details.fullPath}:${details.id})"
}

fun <T : Directory> List<T>.sorted(sortingType: SortingType): List<T> =
    when (sortingType) {
        SortingType.NAME_ASC -> this.sortedBy { it.name }
        SortingType.NAME_DESC -> this.sortedByDescending { it.name }
        SortingType.TIME_ASC -> this.sortedBy { it.details.dateMillis }
        SortingType.TIME_DESC -> this.sortedByDescending { it.details.dateMillis }
    }

fun <T : Directory> List<T>.filtered(string: String): List<T> = this.filter { it.name.lowercase().contains(string.lowercase()) }
