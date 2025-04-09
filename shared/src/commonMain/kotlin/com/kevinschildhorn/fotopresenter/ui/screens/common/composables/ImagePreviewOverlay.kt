package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.TestTags.Directory.IMAGE_PREVIEW
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.composables.LoadingAsyncImage
import com.kevinschildhorn.fotopresenter.ui.testTag
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowLeft
import compose.icons.evaicons.fill.ArrowRight

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
                        .padding(Padding.IMAGE.dp)
                        .testTag(IMAGE_PREVIEW(image.name)),
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
