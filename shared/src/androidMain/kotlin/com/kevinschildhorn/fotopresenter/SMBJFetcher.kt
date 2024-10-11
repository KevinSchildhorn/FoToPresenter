package com.kevinschildhorn.fotopresenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import coil3.ImageLoader
import coil3.asImage
import coil3.decode.DataSource
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.fetch.ImageFetchResult
import coil3.request.Options
import com.hierynomus.smbj.share.File
import com.kevinschildhorn.fotopresenter.data.datasources.ImageCacheDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.kevinschildhorn.fotopresenter.ui.shared.DriverFactory
import com.kevinschildhorn.fotopresenter.ui.shared.SharedCache
import com.kevinschildhorn.fotopresenter.ui.shared.getScaledDimensions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SMBJFetcher(
    private val directoryDetails: NetworkDirectoryDetails,
    context: Context,
) : Fetcher {

    val imageCacheDataSource = ImageCacheDataSource(
        cache = SharedCache,
        driver = DriverFactory(context).createDriver(),
        logger = baseLogger.withTag("ImageCacheDataSource")
    )

    override suspend fun fetch(): FetchResult? {
        return withContext(Dispatchers.IO) {
            imageCacheDataSource.getImage(directoryDetails)
            if (SMBJHandler.isConnected) {
                val image = SMBJHandler.openImage(path = directoryDetails.fullPath)
                val file = image?.file
                if (file != null) {
                    val bitmap = getBitmapFromFile(file, 64)
                    if (bitmap != null) {
                        file.close()
                        ImageFetchResult(
                            image = bitmap.asImage(),
                            isSampled = true,
                            dataSource = DataSource.NETWORK,
                        )
                    } else {
                        throw Exception("Failed to fetch image from FTP")
                    }
                } else {
                    throw Exception("Failed to fetch image from FTP")
                }
            } else {
                throw Exception("Failed to fetch image from FTP")
            }
        }
    }

    private fun getBitmapFromFile(file: File, size: Int): Bitmap? {

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(file.inputStream, null, options)

        val height: Int = options.outHeight
        val width: Int = options.outWidth
        val dimensions = getScaledDimensions(width, height, size)
        val heightRatio: Int = Math.round(height.toFloat() / dimensions.second.toFloat())
        val widthRatio: Int = Math.round(width.toFloat() / dimensions.first.toFloat())
        options.inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio

        options.inJustDecodeBounds = false
        return BitmapFactory.decodeStream(file.inputStream, null, options)
    }

    class Factory() : Fetcher.Factory<NetworkDirectoryDetails> {
        override fun create(
            data: NetworkDirectoryDetails,
            options: Options,
            imageLoader: ImageLoader
        ): Fetcher? = SMBJFetcher(data, options.context)
    }
}