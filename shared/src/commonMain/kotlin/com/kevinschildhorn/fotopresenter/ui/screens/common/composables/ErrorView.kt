package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginScreenAtoms.errorView

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
) {
    val atom = errorView

    var configuredModifier: Modifier = modifier
    atom.backgroundColor?.let {
        configuredModifier = configuredModifier.background(color = it.composeColor)
    }
    Box(
        modifier =
            configuredModifier.padding(
                horizontal = atom.paddingHorizontal?.dp ?: 0.dp,
                vertical = atom.paddingVertical?.dp ?: 0.dp,
            ).fillMaxWidth(),
    ) {
        Text(
            text = message,
            color = atom.textColor.composeColor,
            style = atom.textStyle,
        )
    }
}
