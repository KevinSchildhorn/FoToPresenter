package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowLeft
import compose.icons.evaicons.fill.ArrowRight

@Composable
fun ImagePreviewOverlay(
    image: ImageBitmap,
    onDismiss: () -> Unit,
    onBack: () -> Unit,
    onForward: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Overlay(
        5f,
        modifier =
            Modifier
                .background(FotoColors.shadow.composeColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onDismiss,
                ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // imageState.onSuccess {
            Image(
                bitmap = image,
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.9f)
                        .padding(Padding.IMAGE.dp),
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(Padding.STANDARD.dp)
                        .height(44.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(
                    onClick = onBack,
                    colors =
                        ButtonDefaults.buttonColors(
                            backgroundColor = FotoColors.primary.composeColor,
                        ),
                ) {
                    Icon(
                        EvaIcons.Fill.ArrowLeft,
                        contentDescription = null,
                        tint = FotoColors.surface.composeColor,
                    )
                }
                Button(
                    onClick = onForward,
                    colors =
                        ButtonDefaults.buttonColors(
                            backgroundColor = FotoColors.primary.composeColor,
                        ),
                ) {
                    Icon(
                        EvaIcons.Fill.ArrowRight,
                        contentDescription = null,
                        tint = FotoColors.surface.composeColor,
                    )
                }
            }
        }
        /*
        }.onLoading {
            CircularProgressIndicator(
                modifier = Modifier.width(75.dp).align(Alignment.Center),
                color = FotoColors.primary.composeColor,
            )
        }.onError {
            Text("Error")
        }*/
    }
}
