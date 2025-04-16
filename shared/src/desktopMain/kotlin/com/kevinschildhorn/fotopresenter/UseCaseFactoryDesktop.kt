@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistFileDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistSQLDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.defaultNetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.DisconnectFromServerUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveSlideshowFromPlaylistUseCase
import com.kevinschildhorn.fotopresenter.domain.image.SaveMetadataForPathUseCase
import com.kevinschildhorn.fotopresenter.ui.shared.DriverFactory
import com.kevinschildhorn.fotopresenter.ui.shared.SharedInMemoryCache
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

actual object UseCaseFactory {
    private val baseLogger = Logger

    private val preferences: Preferences = Preferences.userRoot()
    private val settings = PreferencesSettings(preferences)
    private val networkHandler: NetworkHandler = defaultNetworkHandler
    private val directoryDataSource =
        DirectoryDataSource(
            networkHandler,
            baseLogger.withTag("DirectoryDataSource"),
        )
    private val sqlDriver = DriverFactory().createDriver()
    private val credentialDataSource = CredentialsDataSource(settings)
    val credentialsRepository = CredentialsRepository(credentialDataSource)
    private val imageMetadataDataSource =
        ImageMetadataDataSource(
            networkHandler = networkHandler,
            logger = baseLogger.withTag("imageMetadataDataSource"),
        )
    val directoryRepository =
        DirectoryRepository(
            directoryDataSource,
            logger = baseLogger.withTag("DirectoryRepository"),
        )
    private val playlistSQLDataSource =
        PlaylistSQLDataSource(
            sqlDriver,
            baseLogger,
        )
    private val playlistFileDataSource =
        PlaylistFileDataSource(
            baseLogger.withTag("playlistFileDataSource"),
            networkHandler,
        )
    val playlistRepository =
        PlaylistRepository(playlistSQLDataSource, playlistFileDataSource, baseLogger)

    actual val changeDirectoryUseCase: ChangeDirectoryUseCase
        get() =
            ChangeDirectoryUseCase(
                dataSource =
                    DirectoryDataSource(
                        networkHandler,
                        baseLogger.withTag("DirectoryDataSource"),
                    ),
                logger = baseLogger.withTag("ChangeDirectoryUseCase"),
            )
    actual val autoConnectUseCase: AutoConnectUseCase
        get() =
            AutoConnectUseCase(
                client = networkHandler,
                repository = credentialsRepository,
                logger = baseLogger.withTag("AutoConnectUseCase"),
            )
    actual val disconnectFromServerUseCase: DisconnectFromServerUseCase
        get() =
            DisconnectFromServerUseCase(
                credentialsRepository,
                networkHandler,
                baseLogger.withTag("DisconnectFromServerUseCase"),
            )
    actual val retrieveImageDirectoriesUseCase: RetrieveImageDirectoriesUseCase
        get() =
            RetrieveImageDirectoriesUseCase(
                logger = baseLogger.withTag("RetrieveImageDirectoriesUseCase"),
            )
    actual val retrieveSlideshowFromPlaylistUseCase: RetrieveSlideshowFromPlaylistUseCase
        get() =
            RetrieveSlideshowFromPlaylistUseCase(
                logger = baseLogger.withTag("RetrieveSlideshowFromPlaylistUseCase"),
                retrieveDirectoryUseCase = this.retrieveImageDirectoriesUseCase,
            )
    actual val retrieveDirectoryContentsUseCase: RetrieveDirectoryContentsUseCase
        get() =
            RetrieveDirectoryContentsUseCase(
                directoryRepository = directoryRepository,
                logger = baseLogger.withTag("RetrieveDirectoryContentsUseCase"),
            )
    actual val retrieveImageUseCase: RetrieveImageUseCase
        get() =
            RetrieveImageUseCase(
                cachedImageDataSource =
                    CachedImageDataSource(
                        cache = SharedInMemoryCache,
                        logger = baseLogger.withTag("ImageCacheDataSource"),
                    ),
                logger = baseLogger.withTag("RetrieveImageUseCase"),
            )
    actual val saveMetadataForPathUseCase: SaveMetadataForPathUseCase
        get() = SaveMetadataForPathUseCase(dataSource = imageMetadataDataSource)
}
