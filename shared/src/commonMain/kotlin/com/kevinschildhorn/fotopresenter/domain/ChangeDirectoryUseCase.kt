package com.kevinschildhorn.fotopresenter.domain

import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlin.coroutines.cancellation.CancellationException

class ChangeDirectoryUseCase(
    private val dataSource: DirectoryDataSource,
) {
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend operator fun invoke(path: String) =
        try {
            dataSource.changeDirectory(path)
        } catch (e: Exception) {
            throw NetworkHandlerException(e.message ?: "")
        }
}
