package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.domain.AutoConnectUseCase
import com.kevinschildhorn.fotopresenter.domain.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    val baseLogger = Logger(LoggerConfig.default)

    // Data
    single { CredentialsDataSource(get()) }
    single { CredentialsRepository(get()) }

    // Domain
    factory { ConnectToServerUseCase(get()) }
    factory { AutoConnectUseCase(get(), get()) }
    factory { SaveCredentialsUseCase(get(), baseLogger.withTag("SaveCredentialsUseCase")) }
    //factory { AutoConnectUseCase(get()) }

    // UI
    single { LoginViewModel(baseLogger.withTag("LoginViewModel"), get()) }
}

internal expect val platformModule: Module