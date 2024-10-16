package com.kevinschildhorn.fotopresenter.data

import kotlinx.serialization.Serializable

@Serializable
data class MetadataDetails(
    val files: MutableList<MetadataFileDetails>,
)

@Serializable
data class MetadataFileDetails(
    val filePath: String,
    val tags: Set<String>,
) {
    val tagsString: String
        get() = tags.joinToString(", ")
}
