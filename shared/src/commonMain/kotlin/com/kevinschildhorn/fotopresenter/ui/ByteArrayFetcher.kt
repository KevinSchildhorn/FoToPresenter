package com.kevinschildhorn.fotopresenter.ui

import co.touchlab.kermit.Logger
import coil3.ImageLoader
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.request.Options
import com.kevinschildhorn.fotopresenter.extension.LoggerTagSuffix
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ByteArrayFetcher(
    private val byteArray: ByteArray,
    private val logger: Logger,
) : Fetcher {
    override suspend fun fetch(): FetchResult? {
        return withContext(Dispatchers.IO) {
            val image = SharedImage(byteArray)
            val result = image.getFetchResult(64, logger)
            if (result != null) {
                logger.i { "Image Got!" }
                result
            } else {
                logger.e { "No Image Fetched" }
                null
            }
        }
    }

    class Factory(private val logger: Logger) : Fetcher.Factory<ByteArray> {
        override fun create(
            data: ByteArray,
            options: Options,
            imageLoader: ImageLoader,
        ): Fetcher = ByteArrayFetcher(data, logger.withTag("ByteArrayFetcher$LoggerTagSuffix"))
    }
}
