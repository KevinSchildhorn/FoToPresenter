package me.kevinschildhorn.common

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import me.kevinschildhorn.common.architecture.data.datasources.CredentialsDataSource
import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository
import me.kevinschildhorn.common.architecture.domain.AutoLoginUseCase
import me.kevinschildhorn.common.architecture.domain.SaveCredentialsUseCase
import me.kevinschildhorn.common.architecture.ui.viewmodel.LoginViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    val baseLogger = Logger(LoggerConfig.default)

    // Data
    single { CredentialsDataSource(get()) }
    single { CredentialsRepository(get()) }

    // Domain
    factory { SaveCredentialsUseCase(get(), baseLogger.withTag("SaveCredentialsUseCase")) }
    factory { AutoLoginUseCase(get()) }

    // UI
    single { LoginViewModel(baseLogger.withTag("LoginViewModel")) }
}

internal expect val platformModule: Module
