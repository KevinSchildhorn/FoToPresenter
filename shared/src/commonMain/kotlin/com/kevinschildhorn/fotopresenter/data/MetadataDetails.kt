package com.kevinschildhorn.fotopresenter.data

import kotlinx.serialization.Serializable

@Serializable
data class MetadataDetails(
    val files: List<MetadataFileDetails>
)

@Serializable
data class MetadataFileDetails(
    val filePath: String,
    val tags: List<String>,
)