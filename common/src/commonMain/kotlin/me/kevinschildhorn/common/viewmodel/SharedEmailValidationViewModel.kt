package me.kevinschildhorn.android.viewModel

/*
open class SharedEmailValidationViewModel : ViewModel() {

    private var _emailValidationState: MutableStateFlow<EmailValidationState> =
        MutableStateFlow(EmailValidationState.Empty)
    val emailTextState = TextFieldViewModel("Email", _emailValidationState, viewModelScope)

    private var _passwordValidationState: MutableStateFlow<PasswordValidationState> =
        MutableStateFlow(PasswordValidationState.Empty)
    val passwordTextState = TextFieldViewModel("Password", _passwordValidationState, viewModelScope)

    private var _createProfileState: MutableStateFlow<CreatingProfileState> =
        MutableStateFlow(CreatingProfileState.NotReady)
    val createProfileButtonState =
        ButtonViewModel("Create Profile", _createProfileState, viewModelScope)


    fun updateEmail(email: String) {
        emailTextState.setText(email)
        _emailValidationState.value = EmailValidator.verifyEmail(email)
        checkRequirements()
    }

    fun updatePassword(password: String) {
        passwordTextState.setText(password)
        _passwordValidationState.value = PasswordValidator.verifyPassword(password)
        checkRequirements()
    }

    private fun checkRequirements() {
        val ready = _emailValidationState.value.isValid && _passwordValidationState.value.isValid

        _createProfileState.value =
            if (ready) CreatingProfileState.Ready else CreatingProfileState.NotReady
    }

    fun createProfile() {
        viewModelScope.launch {
            _createProfileState.value = CreatingProfileState.Loading
            delay(2500)
            _createProfileState.value = CreatingProfileState.Complete
        }
    }
}*/