package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.domain.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.ChangeDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrievePhotosFromDirectoryUseCase
import com.kevinschildhorn.fotopresenter.domain.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule =
    module {
        val baseLogger = Logger(LoggerConfig.default)

        // Data
        single { CredentialsDataSource(get()) }
        single { CredentialsRepository(get()) }

        // Domain
        factory { ConnectToServerUseCase(get()) }
        factory { ChangeDirectoryUseCase(get()) }
        factory { AutoConnectUseCase(get(), get()) }
        factory { SaveCredentialsUseCase(get(), baseLogger.withTag("SaveCredentialsUseCase")) }
        factory { RetrieveDirectoryUseCase(get()) }
        factory { RetrievePhotosFromDirectoryUseCase(get()) }

        // UI
        single { LoginViewModel(baseLogger.withTag("LoginViewModel"), get()) }
        single { DirectoryViewModel(baseLogger.withTag("DirectoryViewModel")) }
    }

internal expect val platformModule: Module
