package com.kevinschildhorn.fotopresenter.ui

import coil3.ImageLoader
import coil3.decode.DataSource
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.fetch.ImageFetchResult
import coil3.request.Options
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

class ByteArrayFetcher(
    private val byteArray: ByteArray,
    private val networkHandler: NetworkHandler,
) : Fetcher {

    override suspend fun fetch(): FetchResult? {
        return withContext(Dispatchers.IO) {
            val image = SharedImage(byteArray)
            val coilImage = image.getCoilImage(64)
            if (coilImage != null) {
                ImageFetchResult(
                    image = coilImage,
                    isSampled = true,
                    dataSource = DataSource.NETWORK,
                )
            } else {
                throw Exception("Failed to fetch image from FTP")
            }
        }
    }

    class Factory(private val networkHandler: NetworkHandler) : Fetcher.Factory<ByteArray> {
        override fun create(
            data: ByteArray,
            options: Options,
            imageLoader: ImageLoader
        ): Fetcher = ByteArrayFetcher(data, networkHandler)
    }
}