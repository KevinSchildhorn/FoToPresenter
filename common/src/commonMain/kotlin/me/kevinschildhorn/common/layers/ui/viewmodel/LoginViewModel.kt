package me.kevinschildhorn.common.layers.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.kevinschildhorn.common.layers.ui.viewmodel.base.ViewModel
import me.kevinschildhorn.common.layers.domain.SaveCredentialsUseCase

class LoginViewModel : ViewModel() {

    val loginStateStream: Flow<Boolean> = MutableStateFlow(false)

    fun login(username:String, password:String){
        val saveCredentials = SaveCredentialsUseCase()
        saveCredentials(username, password)
    }
}