package com.kevinschildhorn.fotopresenter

import co.touchlab.kermit.Logger
import co.touchlab.kermit.LoggerConfig
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSource
import com.kevinschildhorn.fotopresenter.data.datasources.CredentialsDataSourceTest
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkHandler
import com.kevinschildhorn.fotopresenter.data.network.NetworkHandler
import com.kevinschildhorn.fotopresenter.data.repositories.CredentialsRepository
import com.kevinschildhorn.fotopresenter.domain.ConnectToServerUseCase
import com.kevinschildhorn.fotopresenter.domain.SaveCredentialsUseCase
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModelTest
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

private val baseLogger = Logger(LoggerConfig.default)

fun testingModule(settings:MapSettings = MapSettings()) =
    module {
        single<NetworkHandler> { MockNetworkHandler() }
        single<Settings> { settings }
        single { CredentialsDataSource(get()) }
        single { CredentialsRepository(get()) }
        //single { AutoConnectUseCase(get()) }
        single { ConnectToServerUseCase(get()) }
        single { SaveCredentialsUseCase(get(), baseLogger) }
        single { LoginViewModel(baseLogger) }
    }