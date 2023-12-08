package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.compose.common.Overlay

@Composable
fun ImagePreviewOverlay(
    imageState: State<ImageBitmap>,
    onDismiss: () -> Unit,
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
        imageState.onSuccess {
            Image(
                bitmap = it,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(Padding.IMAGE.dp),
            )
        }.onLoading {
            CircularProgressIndicator(
                modifier = Modifier.width(75.dp).align(Alignment.Center),
                color = FotoColors.primary.composeColor,
            )
        }.onError {
            Text("Error")
        }
    }
}
