package me.kevinschildhorn.common.network

import me.kevinschildhorn.common.network.data.NetworkDirectory
import me.kevinschildhorn.common.network.data.NetworkFile

data class DirectoryInformation(
    val path: String,
    val directories: List<NetworkDirectory>,
    val files: List<NetworkFile>
)