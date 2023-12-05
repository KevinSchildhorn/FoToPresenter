package com.kevinschildhorn.fotopresenter.data

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory

data class DirectoryContents(
    val folders: List<NetworkDirectory> = emptyList(),
    val images: List<NetworkDirectory> = emptyList()
) {
    val allDirectories: List<NetworkDirectory>
        get() = folders + images
}