package me.kevinschildhorn.common.layers.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.kevinschildhorn.common.layers.ui.viewmodel.base.ViewModel
import me.kevinschildhorn.common.layers.domain.SaveCredentialsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
[ViewModel] for logging into the FTP Server
 **/
class LoginViewModel : ViewModel(), KoinComponent {

    val loginStateStream: Flow<Boolean> = MutableStateFlow(false)

    fun login(address: String, username:String, password:String){
        val saveCredentials: SaveCredentialsUseCase by inject()
        saveCredentials(address, username, password)
    }
}