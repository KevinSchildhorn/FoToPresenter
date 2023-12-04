package com.kevinschildhorn.fotopresenter.data.network

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation

class SMBJNetworkDirectory(
    information: FileIdBothDirectoryInformation,
) : NetworkDirectory {
    override val name: String = information.fileName
    override val fileExtension: String? = information.fileName
    override val id: Int = information.fileId.toInt()
}