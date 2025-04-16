package com.kevinschildhorn.fotopresenter.ui.shared

import co.touchlab.kermit.Logger
import coil3.decode.DataSource
import coil3.decode.ImageSource
import coil3.fetch.FetchResult
import coil3.fetch.SourceFetchResult
import okio.FileSystem
import okio.buffer
import okio.source
import java.io.ByteArrayInputStream

class SharedImage(val byteArray: ByteArray) {
    fun getFetchResult(logger: Logger?): FetchResult? {
        if (byteArray.isEmpty()) {
            logger?.e { "Byte Array is Empty!" }
            return null
        }

        logger?.d { "Getting Android Bitmap from Byte Array" }
        logger?.v { "Getting Image from Bitmap" }
        logger?.v { "Returning result" }

        logger?.v { "Getting Fetch Result for Shared Image" }
        val source = ByteArrayInputStream(byteArray).source().buffer()

        return SourceFetchResult(
            source =
                ImageSource(
                    source = source,
                    fileSystem = FileSystem.SYSTEM,
                ),
            mimeType = null,
            dataSource = DataSource.NETWORK,
        )
    }
}