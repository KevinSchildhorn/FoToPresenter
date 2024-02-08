package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.LoginCredentials
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

interface NetworkHandler {
    val isConnected: Boolean

    suspend fun connect(credentials: LoginCredentials): Boolean

    suspend fun disconnect()

    suspend fun getDirectoryDetails(path: String): NetworkDirectoryDetails?

    suspend fun getDirectoryContents(path: String): List<NetworkDirectoryDetails>

    suspend fun openDirectory(path: String): String?

    suspend fun openImage(path: String): SharedImage?

    suspend fun folderExists(path: String): Boolean?

    suspend fun savePlaylist(playlistName:String, json:String): Boolean
    suspend fun getPlaylists(): List<String>

    suspend fun setMetadata(json:String): Boolean
    suspend fun getMetadata(): String?

    suspend fun deletePlaylist(playlistName: String)
}

class NetworkHandlerException : Exception {
    constructor(error: NetworkHandlerError) : super(error.message)
    constructor(message: String) : super(message)
}

enum class NetworkHandlerError(val message: String) {
    NOT_CONNECTED("The Network Handler is not Connected"),
    DIRECTORY_NOT_FOUND("The Directory you selected was not found"),
    FILE_NOT_FOUND("The File you selected was not found"),
}
