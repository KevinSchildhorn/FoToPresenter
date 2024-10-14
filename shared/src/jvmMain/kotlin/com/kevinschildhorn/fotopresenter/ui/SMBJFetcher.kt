package com.kevinschildhorn.fotopresenter.ui

import coil3.ImageLoader
import coil3.decode.DataSource
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.fetch.ImageFetchResult
import coil3.request.Options
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// ImageRepository
class SMBJFetcher(
    private val directoryDetails: NetworkDirectoryDetails,
    private val imageRepository: ImageRepository,
) : Fetcher {

    override suspend fun fetch(): FetchResult? {
        return withContext(Dispatchers.IO) {
            val image = imageRepository.getCoilImage(directoryDetails, 64)
            if(image != null) {
                ImageFetchResult(
                    image = image,
                    isSampled = true,
                    dataSource = DataSource.NETWORK,
                )
            } else {
                null
            }
        }
    }

    class Factory(private val imageRepository: ImageRepository) :
        Fetcher.Factory<NetworkDirectoryDetails> {
        override fun create(
            data: NetworkDirectoryDetails,
            options: Options,
            imageLoader: ImageLoader
        ): Fetcher = SMBJFetcher(data, imageRepository)
    }
}