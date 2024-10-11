package com.kevinschildhorn.fotopresenter

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
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.kevinschildhorn.fotopresenter.ui.screens.directory.PhotoData
import com.kevinschildhorn.fotopresenter.ui.shared.getScaledDimensions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SMBJFetcher(
    private val photoData: PhotoData,
) : Fetcher {

    override suspend fun fetch(): FetchResult? {
        return withContext(Dispatchers.IO) {
            if (SMBJHandler.isConnected) {
                val image = SMBJHandler.openImage(path = photoData.path)
                val file = image?.file
                if(file != null) {
                    val bitmap = getBitmapFromFile(file, 256)
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

    class Factory() : Fetcher.Factory<PhotoData> {
        override fun create(
            data: PhotoData,
            options: Options,
            imageLoader: ImageLoader
        ): Fetcher? = SMBJFetcher(data)
    }
}