package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kevinschildhorn.fotopresenter.Res
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.error
import com.kevinschildhorn.fotopresenter.photo_camera
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.composables.LoadingAsyncImage
import com.kevinschildhorn.fotopresenter.ui.shared.SharedImage
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowLeft
import compose.icons.evaicons.fill.ArrowRight
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImagePreviewOverlay(
    image: NetworkDirectoryDetails,
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            LoadingAsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(Padding.IMAGE.dp),
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(Padding.STANDARD.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                PrimaryIconButton(
                    EvaIcons.Fill.ArrowLeft,
                    modifier = Modifier.size(64.dp),
                    onClick = onBack,
                )
                PrimaryIconButton(
                    EvaIcons.Fill.ArrowRight,
                    modifier = Modifier.size(64.dp),
                    onClick = onForward,
                )
            }
        }
    }
}
