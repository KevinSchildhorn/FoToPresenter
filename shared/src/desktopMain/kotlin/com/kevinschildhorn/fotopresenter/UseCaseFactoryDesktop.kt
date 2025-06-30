@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.NetworkImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistFileDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistSQLDataSource
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.defaultNetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.ui.shared.DriverFactory
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

    private val imageMetadataDataSource: ImageMetadataDataSource =
        NetworkImageMetadataDataSource(
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
    actual val retrieveImageDirectoriesUseCase: RetrieveImageDirectoriesUseCase
        get() =
            RetrieveImageDirectoriesUseCase(
                logger = baseLogger.withTag("RetrieveImageDirectoriesUseCase"),
            )
    actual val retrieveDirectoryContentsUseCase: RetrieveDirectoryContentsUseCase
        get() =
            RetrieveDirectoryContentsUseCase(
                directoryRepository = directoryRepository,
                imageMetadataDataSource = imageMetadataDataSource,
                logger = baseLogger.withTag("RetrieveDirectoryContentsUseCase"),
            )
}
