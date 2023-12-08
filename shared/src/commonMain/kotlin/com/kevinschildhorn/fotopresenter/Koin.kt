package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.DirectoryDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.ImageRemoteDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.data.repositories.DirectoryRepository
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.domain.connection.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.LogoutUseCase
import com.kevinschildhorn.fotopresenter.domain.directory.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.image.RetrieveImageUseCase
import com.kevinschildhorn.fotopresenter.domain.connection.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.SlideshowViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule =
    module {
        val baseLogger = Logger(LoggerConfig.default)

        // Data
        single { CredentialsDataSource(get()) }
        single { CredentialsRepository(get()) }
        single { DirectoryDataSource(get()) }
        single { DirectoryRepository(get()) }
        single { ImageRemoteDataSource(get()) }
        single { ImageRepository(get()) }

        // Domain
        factory { ConnectToServerUseCase(get(), baseLogger.withTag("ConnectToServerUseCase")) }
        factory { ChangeDirectoryUseCase(get(), baseLogger.withTag("ChangeDirectoryUseCase")) }
        factory { AutoConnectUseCase(get(), get(), baseLogger.withTag("AutoConnectUseCase")) }
        factory { SaveCredentialsUseCase(get(), baseLogger.withTag("SaveCredentialsUseCase")) }
        factory { LogoutUseCase(get(), get(), baseLogger.withTag("LogoutUseCase")) }
        factory {
            RetrieveDirectoryContentsUseCase(
                get(),
                get(),
                baseLogger.withTag("RetrieveDirectoryContentsUseCase"),
            )
        }
        factory { RetrieveImageUseCase(baseLogger.withTag("RetrieveImagesUseCase")) }

        // UI
        single { LoginViewModel(baseLogger.withTag("LoginViewModel"), get()) }
        single { DirectoryViewModel(baseLogger.withTag("DirectoryViewModel")) }
        single { SlideshowViewModel(baseLogger.withTag("SlideshowViewModel")) }
    }

internal expect val platformModule: Module
