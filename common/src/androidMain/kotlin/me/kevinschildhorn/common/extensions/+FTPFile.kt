package me.kevinschildhorn.common.extensions

import org.apache.commons.net.ftp.FTPFile

val FTPFile.isPhoto
    get() = this.name.endsWith(".png") ||
        this.name.endsWith(".jpg") ||
        this.name.endsWith(".jpeg")
