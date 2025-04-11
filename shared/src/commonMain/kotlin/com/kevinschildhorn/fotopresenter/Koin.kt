package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.koin.getLoggerWithTag
import com.kevinschildhorn.fotopresenter.data.DirectoryNavigator
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistFileDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistSQLDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.DisconnectFromServerUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveSlideshowFromPlaylistUseCase
import com.kevinschildhorn.fotopresenter.domain.image.SaveMetadataForPathUseCase
import com.kevinschildhorn.fotopresenter.extension.LOGGER_TAG_SUFFIX
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModelNew
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel
import com.kevinschildhorn.fotopresenter.ui.shared.CacheInterface
import com.kevinschildhorn.fotopresenter.ui.shared.SharedInMemoryCache
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule =
    module {

        // Data
        single { NetworkImageDataSource(get()) }
        single { CredentialsDataSource(get()) }
        single { CredentialsRepository(get()) }
        single { DirectoryDataSource(get(), getLoggerWithTag("DirectoryDataSource$LOGGER_TAG_SUFFIX")) }
        single { DirectoryRepository(get(), get(), getLoggerWithTag("DirectoryRepository$LOGGER_TAG_SUFFIX")) }
        // single { CachedImageDataSource(get(), getLoggerWithTag("ImageCacheDataSource$LoggerTagSuffix")) }
        single { PlaylistFileDataSource(getLoggerWithTag("PlaylistDataSource$LOGGER_TAG_SUFFIX"), get()) }
        single { PlaylistSQLDataSource(get(), getLoggerWithTag("PlaylistDataSource$LOGGER_TAG_SUFFIX")) }
        single { PlaylistRepository(get(), get(), getLoggerWithTag("PlaylistRepository$LOGGER_TAG_SUFFIX")) }
        factory { ImageMetadataDataSource(getLoggerWithTag("ImageMetadataDataSource$LOGGER_TAG_SUFFIX"), get()) }
        single { ImageRepository(get(), getLoggerWithTag("ImageRepository$LOGGER_TAG_SUFFIX")) }
        single<CacheInterface> { SharedInMemoryCache }
        single { DirectoryNavigator(get(), getLoggerWithTag("DirectoryNavigator$LOGGER_TAG_SUFFIX")) }
        single { ImagePreviewNavigator(getLoggerWithTag("ImagePreviewNavigator$LOGGER_TAG_SUFFIX")) }

        // Domain
        factory { ChangeDirectoryUseCase(get(), getLoggerWithTag("ChangeDirectoryUseCase$LOGGER_TAG_SUFFIX")) }
        factory { AutoConnectUseCase(get(), get(), getLoggerWithTag("AutoConnectUseCase$LOGGER_TAG_SUFFIX")) }
        factory {
            DisconnectFromServerUseCase(
                get(),
                get(),
                getLoggerWithTag("DisconnectFromServerUseCase$LOGGER_TAG_SUFFIX"),
            )
        }
        factory { RetrieveImageDirectoriesUseCase(getLoggerWithTag("RetrieveImageDirectoriesUseCase$LOGGER_TAG_SUFFIX")) }
        factory {
            RetrieveSlideshowFromPlaylistUseCase(
                getLoggerWithTag("RetrieveSlideshowFromPlaylistUseCase$LOGGER_TAG_SUFFIX"),
                get(),
            )
        }
        factory {
            RetrieveDirectoryContentsUseCase(
                get(),
                getLoggerWithTag("RetrieveDirectoryContentsUseCase$LOGGER_TAG_SUFFIX"),
            )
        }
        factory { RetrieveImageUseCase(get(), getLoggerWithTag("RetrieveImageUseCase$LOGGER_TAG_SUFFIX")) }
        factory { SaveMetadataForPathUseCase(get()) }
        // UI
        single { LoginViewModel(getLoggerWithTag("LoginViewModel$LOGGER_TAG_SUFFIX"), get(), get()) }
        single { DirectoryViewModelNew(get(), get(), get(), get(), get(), getLoggerWithTag("DirectoryViewModelNew$LOGGER_TAG_SUFFIX")) }
        single { SlideshowViewModel(getLoggerWithTag("SlideshowViewModel$LOGGER_TAG_SUFFIX")) }
        single { PlaylistViewModel(get(), getLoggerWithTag("PlaylistViewModel$LOGGER_TAG_SUFFIX")) }
    }

internal expect val platformModule: Module
