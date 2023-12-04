package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler

/**
Retrieving Photos from current Directory
 **/
class RetrievePhotosFromDirectoryUseCase(
    private val client: NetworkHandler,
) {
    suspend operator fun invoke(): List<NetworkDirectory> =
        client.getDirectoryContents()
}