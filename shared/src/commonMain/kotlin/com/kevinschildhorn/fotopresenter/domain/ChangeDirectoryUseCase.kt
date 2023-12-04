package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectory
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlin.coroutines.cancellation.CancellationException

class ChangeDirectoryUseCase(
    private val client: NetworkHandler,
) {
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend operator fun invoke(path: String) =
        try {
            client.openDirectory(path)
        } catch (e: Exception) {
            throw NetworkHandlerException(e.message ?: "")
        }
}