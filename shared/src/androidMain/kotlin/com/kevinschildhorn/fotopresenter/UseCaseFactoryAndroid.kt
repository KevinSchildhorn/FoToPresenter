package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveSlideshowFromPlaylistUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual object UseCaseFactory : KoinComponent {
    
    val connectToServerUseCase: ConnectToServerUseCase
        get() {
            val useCase: ConnectToServerUseCase by inject()
            return useCase
        }
    val changeDirectoryUseCase: ChangeDirectoryUseCase
        get() {
            val useCase: ChangeDirectoryUseCase by inject()
            return useCase
        }
    val autoConnectUseCase: AutoConnectUseCase
        get() {
            val useCase: AutoConnectUseCase by inject()
            return useCase
        }
    val saveCredentialsUseCase: SaveCredentialsUseCase
        get() {
            val useCase: SaveCredentialsUseCase by inject()
            return useCase
        }
    val retrieveImageDirectoriesUseCase: RetrieveImageDirectoriesUseCase
        get() {
            val useCase: RetrieveImageDirectoriesUseCase by inject()
            return useCase
        }
    val retrieveSlideshowFromPlaylistUseCase: RetrieveSlideshowFromPlaylistUseCase
        get() {
            val useCase: RetrieveSlideshowFromPlaylistUseCase by inject()
            return useCase
        }
    val retrieveDirectoryContentsUseCase: RetrieveDirectoryContentsUseCase
        get() {
            val useCase: RetrieveDirectoryContentsUseCase by inject()
            return useCase
        }
    val retrieveImageUseCase: RetrieveImageUseCase
        get() {
            val useCase: RetrieveImageUseCase by inject()
            return useCase
        }
}