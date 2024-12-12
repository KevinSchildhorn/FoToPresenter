package com.kevinschildhorn.fotopresenter.ui

import co.touchlab.kermit.Logger
import coil3.ImageLoader
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.request.Options
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.extension.LoggerTagSuffix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SMBJFetcher(
    private val directoryDetails: NetworkDirectoryDetails,
    private val imageRepository: ImageRepository,
    private val logger: Logger,
) : Fetcher {
    override suspend fun fetch(): FetchResult? =
        withContext(Dispatchers.IO) {
            logger.i { "Fetching image: ${directoryDetails.name}" }

            val image = imageRepository.getFetchResult(directoryDetails, 64)
            if (image != null) {
                logger.i { "Image Got! ${directoryDetails.name}" }
                image
            } else {
                logger.e { "No Image Fetched: ${directoryDetails.name}" }
                null
            }
        }

    class Factory(private val imageRepository: ImageRepository, private val logger: Logger) :
        Fetcher.Factory<NetworkDirectoryDetails> {
        override fun create(
            data: NetworkDirectoryDetails,
            options: Options,
            imageLoader: ImageLoader,
        ): Fetcher = SMBJFetcher(data, imageRepository, logger.withTag("SMBJFetcher$LoggerTagSuffix"))
    }
}
