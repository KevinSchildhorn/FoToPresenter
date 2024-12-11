package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.koin.getLoggerWithTag
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
import com.kevinschildhorn.fotopresenter.extension.LoggerTagSuffix
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule =
    module {

        // Data
        single { NetworkImageDataSource(get()) }
        single { CredentialsDataSource(get()) }
        single { CredentialsRepository(get()) }
        single { DirectoryDataSource(get(), getLoggerWithTag("DirectoryDataSource$LoggerTagSuffix")) }
        single { DirectoryRepository(get(), get()) }
        single { CachedImageDataSource(get(), getLoggerWithTag("ImageCacheDataSource$LoggerTagSuffix"), get()) }
        single { PlaylistFileDataSource(getLoggerWithTag("PlaylistDataSource$LoggerTagSuffix"), get()) }
        single { PlaylistSQLDataSource(get(), getLoggerWithTag("PlaylistDataSource$LoggerTagSuffix")) }
        single { PlaylistRepository(get(), get(), getLoggerWithTag("PlaylistRepository$LoggerTagSuffix")) }
        factory { ImageMetadataDataSource(getLoggerWithTag("ImageMetadataDataSource$LoggerTagSuffix"), get()) }
        single { ImageRepository(get(), get(), getLoggerWithTag("ImageRepository$LoggerTagSuffix")) }

        // Domain
        factory { ConnectToServerUseCase(get(), getLoggerWithTag("ConnectToServerUseCase$LoggerTagSuffix")) }
        factory { ChangeDirectoryUseCase(get(), getLoggerWithTag("ChangeDirectoryUseCase$LoggerTagSuffix")) }
        factory { AutoConnectUseCase(get(), get(), getLoggerWithTag("AutoConnectUseCase$LoggerTagSuffix")) }
        factory { SaveCredentialsUseCase(get(), getLoggerWithTag("SaveCredentialsUseCase$LoggerTagSuffix")) }
        factory {
            DisconnectFromServerUseCase(
                get(),
                get(),
                getLoggerWithTag("DisconnectFromServerUseCase$LoggerTagSuffix"),
            )
        }
        factory { RetrieveImageDirectoriesUseCase(getLoggerWithTag("RetrieveImageDirectoriesUseCase$LoggerTagSuffix")) }
        factory {
            RetrieveSlideshowFromPlaylistUseCase(
                getLoggerWithTag("RetrieveSlideshowFromPlaylistUseCase$LoggerTagSuffix"),
                get(),
            )
        }
        factory {
            RetrieveDirectoryContentsUseCase(
                get(),
                getLoggerWithTag("RetrieveDirectoryContentsUseCase$LoggerTagSuffix"),
            )
        }
        factory { RetrieveImageUseCase(get(), getLoggerWithTag("RetrieveImageUseCase$LoggerTagSuffix")) }
        factory { SaveMetadataForPathUseCase(get()) }
        // UI
        single { LoginViewModel(getLoggerWithTag("LoginViewModel$LoggerTagSuffix"), get()) }
        single { DirectoryViewModel(get(), getLoggerWithTag("DirectoryViewModel$LoggerTagSuffix")) }
        single { SlideshowViewModel(getLoggerWithTag("SlideshowViewModel$LoggerTagSuffix")) }
        single { PlaylistViewModel(get(), getLoggerWithTag("PlaylistViewModel$LoggerTagSuffix")) }
    }


internal expect val platformModule: Module
