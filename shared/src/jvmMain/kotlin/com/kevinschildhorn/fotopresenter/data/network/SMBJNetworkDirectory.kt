package com.kevinschildhorn.fotopresenter.data.network

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation

class SMBJNetworkDirectory(
    information: FileIdBothDirectoryInformation,
    override val fullPath: String,
) : NetworkDirectory {
    override val id: Int = information.fileId.toInt()
}
