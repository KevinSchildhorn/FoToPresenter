package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageCacheDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageRemoteDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistDataSource
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveSlideshowFromPlaylistUseCase
import com.kevinschildhorn.fotopresenter.ui.shared.DriverFactory
import com.kevinschildhorn.fotopresenter.ui.shared.SharedCache
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

actual object UseCaseFactory {

    val baseLogger = Logger(LoggerConfig.default)
    private val preferences: Preferences = Preferences.userRoot()
    private val settings = PreferencesSettings(preferences)

    private val directoryDataSource = DirectoryDataSource(
        SMBJHandler,
        baseLogger.withTag("DirectoryDataSource")
    )
    private val credentialDataSource = CredentialsDataSource(settings)
    val credentialsRepository = CredentialsRepository(credentialDataSource)
    private val directoryRepository = DirectoryRepository(directoryDataSource)
    private val imageRepository = ImageRepository(ImageRemoteDataSource(SMBJHandler))
    private val playlistDataSource = PlaylistDataSource(DriverFactory().createDriver(), com.kevinschildhorn.fotopresenter.baseLogger)
    val playlistRepository = PlaylistRepository(playlistDataSource)


    val connectToServerUseCase: ConnectToServerUseCase
        get() = ConnectToServerUseCase(
            client = SMBJHandler,
            logger = baseLogger.withTag("ConnectToServerUseCase")
        )
    val changeDirectoryUseCase: ChangeDirectoryUseCase
        get() = ChangeDirectoryUseCase(
            dataSource = DirectoryDataSource(
                SMBJHandler,
                baseLogger.withTag("DirectoryDataSource")
            ),
            logger = baseLogger.withTag("ChangeDirectoryUseCase")
        )
    val autoConnectUseCase: AutoConnectUseCase
        get() = AutoConnectUseCase(
            client = SMBJHandler,
            repository = credentialsRepository,
            logger = baseLogger.withTag("AutoConnectUseCase")
        )
    val saveCredentialsUseCase: SaveCredentialsUseCase
        get() = SaveCredentialsUseCase(
            repository = credentialsRepository,
            logger = baseLogger.withTag("SaveCredentialsUseCase")
        )
    val retrieveImageDirectoriesUseCase: RetrieveImageDirectoriesUseCase
        get() = RetrieveImageDirectoriesUseCase(
            logger = baseLogger.withTag("RetrieveImageDirectoriesUseCase")
        )
    val retrieveSlideshowFromPlaylistUseCase: RetrieveSlideshowFromPlaylistUseCase
        get() = RetrieveSlideshowFromPlaylistUseCase(
            logger = baseLogger.withTag("RetrieveSlideshowFromPlaylistUseCase"),
            retrieveDirectoryUseCase = this.retrieveImageDirectoriesUseCase,
        )
    val retrieveDirectoryContentsUseCase: RetrieveDirectoryContentsUseCase
        get() = RetrieveDirectoryContentsUseCase(
            directoryRepository = directoryRepository,
            imageRepository = imageRepository,
            logger = baseLogger.withTag("RetrieveDirectoryContentsUseCase")
        )
    val retrieveImageUseCase: RetrieveImageUseCase
        get() = RetrieveImageUseCase(
            imageCacheDataSource = ImageCacheDataSource(
                cache = SharedCache,
                driver = DriverFactory().createDriver(),
                logger = baseLogger.withTag("ImageCacheDataSource")
            ),
            logger = baseLogger.withTag("RetrieveImageUseCase")
        )
}