package me.kevinschildhorn.common

import me.kevinschildhorn.common.layers.data.datasources.CredentialsDataSource
import me.kevinschildhorn.common.layers.data.repositories.CredentialsRepository
import me.kevinschildhorn.common.layers.domain.SaveCredentialsUseCase
import me.kevinschildhorn.common.layers.ui.viewmodel.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    single { CredentialsDataSource(get()) }
    single { CredentialsRepository(get()) }
    factory { SaveCredentialsUseCase(get()) }
    single { LoginViewModel(get()) }
}

internal expect val platformModule: Module