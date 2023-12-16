package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors

@Composable
fun Overlay(
    z: Float,
    visible: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable BoxScope.() -> Unit,
) {
    OverlayShadow(
        z - 1,
        visible,
        onDismiss = onDismiss
    )
    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit,
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .zIndex(z),
            content = content,
        )
    }
}

@Composable
fun OverlayShadow(
    z: Float,
    visible: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = modifier
                .zIndex(z)
                .fillMaxSize()
                .background(FotoColors.shadow.composeColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onDismiss,
                ),
        )
    }
}