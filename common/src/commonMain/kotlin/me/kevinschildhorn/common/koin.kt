package me.kevinschildhorn.common

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import me.kevinschildhorn.common.architecture.data.datasources.CredentialsDataSource
import me.kevinschildhorn.common.architecture.data.repositories.CredentialsRepository
import me.kevinschildhorn.common.architecture.domain.SaveCredentialsUseCase
import me.kevinschildhorn.common.architecture.ui.viewmodel.LoginViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun kermitModule(
    baseLogger: Logger,
) = module {
    factory { (tag: String?) ->
        if (tag != null) baseLogger.withTag(tag) else baseLogger
    }
}

val commonModule = module {
    val baseLogger = Logger(LoggerConfig.default)

    single { CredentialsDataSource(get()) }
    single { CredentialsRepository(get()) }
    factory { SaveCredentialsUseCase(get(), baseLogger.withTag("SaveCredentialsUseCase")) }
    single { LoginViewModel(get()) }
}

internal expect val platformModule: Module
