package com.kevinschildhorn.fotopresenter.data.network

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation

class SMBJNetworkDirectoryDetails(
    information: FileIdBothDirectoryInformation,
    override val fullPath: String,
) : NetworkDirectoryDetails {
    override val id: Int = information.fileId.toInt()
}
