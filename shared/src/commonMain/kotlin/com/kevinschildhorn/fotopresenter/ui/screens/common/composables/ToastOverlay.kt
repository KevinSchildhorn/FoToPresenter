package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.CommonAtoms
import kotlinx.coroutines.delay

@Composable
fun ToastOverlay(
    text: String,
    visible: Boolean,
    onClose: () -> Unit,
) {
    LaunchedEffect(key1 = visible) {
        delay(2000)
        onClose()
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { -25 } + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically { -25 } + fadeOut()
    ) {
        Overlay(
            z = 8f,
            visible = visible,
            shadow = false,
            onDismiss = {},
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier)
                Column {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(FotoColors.secondary.composeColor),
                    ) {
                        Text(
                            text,
                            modifier = Modifier
                                .padding(
                                    horizontal = Padding.STANDARD.dp,
                                    vertical = Padding.MEDIUM.dp
                                ),
                            color = CommonAtoms.toastOverlay.textColor.composeColor,
                            style = CommonAtoms.toastOverlay.textStyle,
                        )
                    }
                    Spacer(Modifier.height(25.dp))
                }
            }
        }
    }
}