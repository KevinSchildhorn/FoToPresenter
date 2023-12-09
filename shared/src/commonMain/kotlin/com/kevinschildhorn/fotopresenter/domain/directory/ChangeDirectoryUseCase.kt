package com.kevinschildhorn.fotopresenter.domain.directory

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandlerException
import kotlin.coroutines.cancellation.CancellationException

/**
Opening the directory from the Path
 **/
class ChangeDirectoryUseCase(
    private val dataSource: DirectoryDataSource,
    private val logger: Logger,
) {
    @Throws(NetworkHandlerException::class, CancellationException::class)
    suspend operator fun invoke(path: String): String =
        try {
            logger.i { "Changing Directory to path $path" }
            dataSource.changeDirectory(path)
        } catch (e: Exception) {
            logger.e(e) { "Error Changing Directory" }
            throw NetworkHandlerException(e.message ?: "")
        }
}
