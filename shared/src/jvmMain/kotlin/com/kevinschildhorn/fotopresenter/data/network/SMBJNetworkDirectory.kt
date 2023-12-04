package com.kevinschildhorn.fotopresenter.data.network

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation

class SMBJNetworkDirectory(
    private val information: FileIdBothDirectoryInformation,
) : NetworkDirectory {

    override val name: String = information.fileName.split(".").first()
    override val fileExtension: String?
        get() {
            val items = information.fileName.split(".")
            return if (items.count() == 1) return null
            else items.last()
        }
    override val id: Int = information.fileId.toInt()
}