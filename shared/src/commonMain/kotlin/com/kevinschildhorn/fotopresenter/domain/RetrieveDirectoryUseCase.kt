package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler

/**
Retrieving Directories from Location
 **/
class RetrieveDirectoryUseCase(
    private val client: NetworkHandler,
) {
    suspend operator fun invoke(): List<NetworkDirectory> {
        val results = client.getDirectoryContents()
        return results.filter { it.fileExtension.isNullOrEmpty() }
    }
}