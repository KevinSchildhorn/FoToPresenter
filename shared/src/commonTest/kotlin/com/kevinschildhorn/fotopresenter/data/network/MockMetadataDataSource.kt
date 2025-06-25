package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource

class MockMetadataDataSource : ImageMetadataDataSource {
    private val metadataMap: MutableMap<Path, MetadataFileDetails> =
        mutableMapOf<Path, MetadataFileDetails>(
            Path("Peeng.png") to
                    MetadataFileDetails(
                        filePath = Path("Peeng.png"),
                        tags = setOf("P", "png"),
                    ),
            Path("Jaypeg.jpg") to
                    MetadataFileDetails(
                        filePath = Path("Jaypeg.jpg"),
                        tags = setOf("J", "jpg"),
                    ),
        )

    override suspend fun readMetadataFromFile(filePath: Path): MetadataFileDetails? {
        return metadataMap[filePath]
    }

    override suspend fun writeMetadataToFile(
        metadata: String,
        filePath: Path
    ): Boolean {
        metadataMap.put(
            filePath, MetadataFileDetails(
                filePath = filePath,
                tags = emptySet<String>()
            )
        )
        return true
    }
}