package me.kevinschildhorn.common

import me.kevinschildhorn.common.layers.data.datasources.CredentialsDataSource
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun startKoin() {
    startKoin {
        modules(commonModule, platformModule)
    }
}

val commonModule = module {
    single { CredentialsDataSource(get()) }
}

internal expect val platformModule: Module