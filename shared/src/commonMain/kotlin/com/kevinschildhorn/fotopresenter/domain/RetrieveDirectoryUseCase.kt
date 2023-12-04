package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler

/**
Retrieving Directories and Photos from Location
 **/
class RetrieveDirectoryUseCase(
    private val client: NetworkHandler,
) {
    operator suspend fun invoke(): List<NetworkDirectory> =
        client.getDirectoryContents()
}