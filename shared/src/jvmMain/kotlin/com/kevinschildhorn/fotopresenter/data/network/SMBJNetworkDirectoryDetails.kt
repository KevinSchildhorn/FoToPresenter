package com.kevinschildhorn.fotopresenter.data.network

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation
import com.kevinschildhorn.fotopresenter.data.Path

class SMBJNetworkDirectoryDetails(
    information: FileIdBothDirectoryInformation,
    override val fullPath: Path,
) : NetworkDirectoryDetails {
    override val id: Long = information.fileId

    override val dateMillis: Long = information.changeTime.toEpochMillis()
}
