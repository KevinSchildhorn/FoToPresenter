package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistFileDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistSQLDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.CachedImageDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.DisconnectFromServerUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveSlideshowFromPlaylistUseCase
import com.kevinschildhorn.fotopresenter.domain.image.SaveMetadataForPathUseCase
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val baseLogger = Logger.withTag("")

val commonModule =
    module {

        // Data
        single { NetworkImageDataSource(get()) }
        single { CredentialsDataSource(get()) }
        single { CredentialsRepository(get()) }
        single { DirectoryDataSource(get(), baseLogger.withTag("DirectoryDataSource")) }
        single { DirectoryRepository(get(), get()) }
        single { CachedImageDataSource(get(), baseLogger.withTag("ImageCacheDataSource"), get()) }
        single { PlaylistFileDataSource(baseLogger.withTag("PlaylistDataSource"), get()) }
        single { PlaylistSQLDataSource(get(), baseLogger.withTag("PlaylistDataSource")) }
        single { PlaylistRepository(get(), get()) }
        factory { ImageMetadataDataSource(baseLogger.withTag("ImageMetadataDataSource"), get()) }
        single { ImageRepository(get(), get(), baseLogger.withTag("ImageRepository")) }

        // Domain
        factory { ConnectToServerUseCase(get(), baseLogger.withTag("ConnectToServerUseCase")) }
        factory { ChangeDirectoryUseCase(get(), baseLogger.withTag("ChangeDirectoryUseCase")) }
        factory { AutoConnectUseCase(get(), get(), baseLogger.withTag("AutoConnectUseCase")) }
        factory { SaveCredentialsUseCase(get(), baseLogger.withTag("SaveCredentialsUseCase")) }
        factory {
            DisconnectFromServerUseCase(
                get(),
                get(),
                baseLogger.withTag("DisconnectFromServerUseCase"),
            )
        }
        factory { RetrieveImageDirectoriesUseCase(baseLogger.withTag("RetrieveImageDirectoriesUseCase")) }
        factory {
            RetrieveSlideshowFromPlaylistUseCase(
                baseLogger.withTag("RetrieveSlideshowFromPlaylistUseCase"),
                get(),
            )
        }
        factory {
            RetrieveDirectoryContentsUseCase(
                get(),
                baseLogger.withTag("RetrieveDirectoryContentsUseCase"),
            )
        }
        factory { RetrieveImageUseCase(get(), baseLogger.withTag("RetrieveImageUseCase")) }
        factory { SaveMetadataForPathUseCase(get()) }
        // UI
        single { LoginViewModel(baseLogger.withTag("LoginViewModel"), get()) }
        single { DirectoryViewModel(get(), baseLogger.withTag("DirectoryViewModel")) }
        single { SlideshowViewModel(baseLogger.withTag("SlideshowViewModel")) }
        single { PlaylistViewModel(get(), baseLogger.withTag("PlaylistViewModel")) }
    }

internal expect val platformModule: Module
