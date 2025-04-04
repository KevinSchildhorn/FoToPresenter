package com.kevinschildhorn.fotopresenter.data.network

import com.kevinschildhorn.fotopresenter.data.login.LoginCredentials
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage

interface NetworkHandler {
    val isConnected: Boolean

    suspend fun connect(credentials: LoginCredentials): Boolean

    suspend fun disconnect()

    suspend fun getDirectoryDetails(path: Path): NetworkDirectoryDetails?

    suspend fun getDirectoryContents(path: Path): List<NetworkDirectoryDetails>

    // Gets a handle to a directory in the given path
    suspend fun openDirectory(path: Path): Path?

    suspend fun getSharedImage(path: Path): SharedImage?

    suspend fun folderExists(path: Path): Boolean?

    suspend fun savePlaylist(
        playlistName: String,
        json: String,
    ): Boolean

    suspend fun getPlaylists(): List<String>

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
