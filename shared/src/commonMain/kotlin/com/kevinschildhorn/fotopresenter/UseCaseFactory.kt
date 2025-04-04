package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.DisconnectFromServerUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveSlideshowFromPlaylistUseCase
import com.kevinschildhorn.fotopresenter.domain.image.SaveMetadataForPathUseCase

expect object UseCaseFactory {
    val changeDirectoryUseCase: ChangeDirectoryUseCase
    val autoConnectUseCase: AutoConnectUseCase
    val saveCredentialsUseCase: SaveCredentialsUseCase
    val disconnectFromServerUseCase: DisconnectFromServerUseCase
    val retrieveImageDirectoriesUseCase: RetrieveImageDirectoriesUseCase
    val retrieveSlideshowFromPlaylistUseCase: RetrieveSlideshowFromPlaylistUseCase
    val retrieveDirectoryContentsUseCase: RetrieveDirectoryContentsUseCase
    val retrieveImageUseCase: RetrieveImageUseCase
    val saveMetadataForPathUseCase: SaveMetadataForPathUseCase
}
