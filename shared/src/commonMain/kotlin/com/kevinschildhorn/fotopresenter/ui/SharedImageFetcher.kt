package com.kevinschildhorn.fotopresenter.ui

import co.touchlab.kermit.Logger
import coil3.ImageLoader
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.request.Options
import com.kevinschildhorn.fotopresenter.extension.LOGGER_TAG_SUFFIX
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedImageFetcher(
    private val sharedImage: SharedImage,
    private val logger: Logger,
) : Fetcher {
    override suspend fun fetch(): FetchResult? =
        withContext(Dispatchers.IO) {
            val result = sharedImage.getFetchResult(logger)
            if (result != null) {
                logger.i { "Image Got!" }
                result
            } else {
                logger.e { "No Image Fetched" }
                null
            }
        }

    class Factory(
        private val logger: Logger,
    ) : Fetcher.Factory<SharedImage> {
        override fun create(
            data: SharedImage,
            options: Options,
            imageLoader: ImageLoader,
        ): Fetcher = SharedImageFetcher(data, logger.withTag("ByteArrayFetcher$LOGGER_TAG_SUFFIX"))
    }
}
