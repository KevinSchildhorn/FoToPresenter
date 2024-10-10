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
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowLeft
import compose.icons.evaicons.fill.ArrowRight

@Composable
fun ImagePreviewOverlay(
    image: ImageBitmap,
    visible: Boolean,
    onDismiss: () -> Unit,
    onBack: () -> Unit,
    onForward: () -> Unit,
) {

    Overlay(
        5f,
        visible = visible,
        onDismiss = onDismiss,
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
                PrimaryIconButton(
                    EvaIcons.Fill.ArrowLeft,
                    onClick = onBack,
                )
                PrimaryIconButton(
                    EvaIcons.Fill.ArrowRight,
                    onClick = onForward,
                )
            }
        }
        /*
        }.onLoading {
            CircularProgressIndicator(
                modifier = Modifier.width(75.dp).align(Alignment.Center),
                color = FotoColors.primary,
            )
        }.onError {
            Text("Error")
        }*/
    }
}
