package com.kevinschildhorn.fotopresenter.ui.shared

import coil3.Image
import coil3.decode.DataSource
import coil3.decode.ImageSource
import coil3.fetch.FetchResult
import coil3.fetch.SourceFetchResult
import okio.FileSystem
import okio.buffer
import okio.source
import java.io.ByteArrayInputStream


actual open class SharedImage actual constructor(actual val byteArray: ByteArray) {
    actual fun getFetchResult(size: Int): FetchResult? {
        val source = ByteArrayInputStream(byteArray).source().buffer()

        return SourceFetchResult(
            source = ImageSource(
                source = source,
                fileSystem = FileSystem.SYSTEM,
            ),
            mimeType = null,
            dataSource = DataSource.NETWORK,
        )
    }
}

