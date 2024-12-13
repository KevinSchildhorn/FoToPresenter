@file:Suppress("ktlint:standard:filename")

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

actual open class SharedImage actual constructor(actual val byteArray: ByteArray) {
    actual fun getFetchResult(size: Int, logger: Logger?): FetchResult? {
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
