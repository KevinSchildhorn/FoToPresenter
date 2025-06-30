package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.koin.getLoggerWithTag
import com.kevinschildhorn.fotopresenter.data.DirectoryNavigator
import com.kevinschildhorn.fotopresenter.data.ImagePreviewNavigator
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.NetworkImageMetadataDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistFileDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.PlaylistSQLDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.image.NetworkImageDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.data.repositories.PlaylistRepository
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveImageDirectoriesUseCase
import com.kevinschildhorn.fotopresenter.extension.LOGGER_TAG_SUFFIX
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
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
        single {
            DirectoryDataSource(
                get(),
                getLoggerWithTag("DirectoryDataSource$LOGGER_TAG_SUFFIX"),
            )
        }
        single {
            DirectoryRepository(
                get(),
                getLoggerWithTag("DirectoryRepository$LOGGER_TAG_SUFFIX"),
            )
        }
        // single { CachedImageDataSource(get(), getLoggerWithTag("ImageCacheDataSource$LoggerTagSuffix")) }
        single {
            PlaylistFileDataSource(
                getLoggerWithTag("PlaylistDataSource$LOGGER_TAG_SUFFIX"),
                get(),
            )
        }
        single {
            PlaylistSQLDataSource(
                get(),
                getLoggerWithTag("PlaylistDataSource$LOGGER_TAG_SUFFIX"),
            )
        }
        single {
            PlaylistRepository(
                get(),
                get(),
                getLoggerWithTag("PlaylistRepository$LOGGER_TAG_SUFFIX"),
            )
        }
        single<ImageMetadataDataSource> {
            NetworkImageMetadataDataSource(
                getLoggerWithTag("ImageMetadataDataSource$LOGGER_TAG_SUFFIX"),
                get(),
            )
        }
        single { ImageRepository(get(), getLoggerWithTag("ImageRepository$LOGGER_TAG_SUFFIX")) }
        single<CacheInterface> { SharedInMemoryCache }
        single {
            DirectoryNavigator(
                get(),
                getLoggerWithTag("DirectoryNavigator$LOGGER_TAG_SUFFIX"),
            )
        }
        factory { ImagePreviewNavigator(getLoggerWithTag("ImagePreviewNavigator$LOGGER_TAG_SUFFIX")) }

        // Domain
        factory { RetrieveImageDirectoriesUseCase(getLoggerWithTag("RetrieveImageDirectoriesUseCase$LOGGER_TAG_SUFFIX")) }
        factory {
            RetrieveDirectoryContentsUseCase(
                get(),
                get(),
                getLoggerWithTag("RetrieveDirectoryContentsUseCase$LOGGER_TAG_SUFFIX"),
            )
        }
        // UI
        single {
            LoginViewModel(
                getLoggerWithTag("LoginViewModel$LOGGER_TAG_SUFFIX"),
                get(),
                get(),
            )
        }
        single {
            DirectoryViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                getLoggerWithTag("DirectoryViewModelNew$LOGGER_TAG_SUFFIX"),
            )
        }
        single {
            SlideshowViewModel(
                get(),
                get(),
                getLoggerWithTag("SlideshowViewModel$LOGGER_TAG_SUFFIX"),
            )
        }
        single { PlaylistViewModel(get(), getLoggerWithTag("PlaylistViewModel$LOGGER_TAG_SUFFIX")) }
    }

internal expect val platformModule: Module
