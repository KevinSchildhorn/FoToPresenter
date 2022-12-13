package me.kevinschildhorn.common.deprecated.uilogic

import me.kevinschildhorn.common.deprecated.businesslogic.CreatingProfileState
import me.kevinschildhorn.common.design.color.SharedColor
import me.kevinschildhorn.common.design.color.SharedEnabledColor
import me.kevinschildhorn.common.design.theme.DesignColors
import me.kevinschildhorn.common.design.theme.DesignEnabledColors

data class ButtonState(
    val text: String,
    val textColor: SharedColor = DesignColors.invertedTextColor,
    val enabledColor: SharedEnabledColor = DesignEnabledColors.default,
    val isEnabled: Boolean = false,
    val isLoading: Boolean = false,
) {
    fun updateWithState(profileState: CreatingProfileState) =
        when (profileState) {
            CreatingProfileState.Ready ->
                this.copy(
                    isEnabled = true,
                    isLoading = false
                )
            CreatingProfileState.Loading ->
                this.copy(
                    isEnabled = true,
                    isLoading = true
                )
            CreatingProfileState.NotReady,
            CreatingProfileState.Complete,
            CreatingProfileState.Error ->
                this.copy(
                    isEnabled = false,
                    isLoading = false
                )
        }

    companion object {
        fun create(text: String) =
            ButtonState(text = text)
    }
}
