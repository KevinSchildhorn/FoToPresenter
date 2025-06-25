package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.overlay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.ShuffleType
import com.kevinschildhorn.fotopresenter.ui.composables.FotoCheckbox
import com.kevinschildhorn.fotopresenter.ui.composables.FotoRadioButton
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.FotoDialog

@Composable
fun SlideshowOverlay(
    onConfirmation: (Boolean, ShuffleType) -> Unit,
    onDismiss: () -> Unit,
) {
    var subfolders: Boolean by remember { mutableStateOf(false) }
    var shuffleType: ShuffleType by remember { mutableStateOf(ShuffleType.NONE) }
    FotoDialog(
        dialogTitle = "Slideshow",
        onDismissRequest = onDismiss,
        onConfirmation = {
            onConfirmation(subfolders, shuffleType)
        },
    ) {
        FotoCheckbox(
            "Include Subfolders",
            checked = subfolders,
            horizontalArrangement = Arrangement.Start,
            onCheckedChange = {
                subfolders = it
                if (!subfolders) {
                    if (shuffleType == ShuffleType.SHUFFLE_FOLDERS ||
                        shuffleType == ShuffleType.SHUFFLE_ALL_KEEPING_GROUPING ||
                        shuffleType == ShuffleType.SHUFFLE_ALL_KEEPING_GROUPING
                    ) {
                        shuffleType = ShuffleType.SHUFFLE_ALL
                    }
                }
            },
        )

        Text("Shuffle Type:", style = MaterialTheme.typography.subtitle2)
        FotoRadioButton(
            "Don't Shuffle",
            selected = shuffleType == ShuffleType.NONE,
            enabled = true,
            horizontalArrangement = Arrangement.Start,
            onRadioChanged = {
                shuffleType = ShuffleType.NONE
            },
            modifier = Modifier.fillMaxWidth(),
        )
        FotoRadioButton(
            "Shuffle",
            selected = shuffleType == ShuffleType.SHUFFLE_ALL,
            enabled = true,
            horizontalArrangement = Arrangement.Start,
            onRadioChanged = {
                shuffleType = ShuffleType.SHUFFLE_ALL
            },
        )
        FotoRadioButton(
            "Only shuffle the folders",
            selected = shuffleType == ShuffleType.SHUFFLE_FOLDERS,
            enabled = subfolders,
            horizontalArrangement = Arrangement.Start,
            onRadioChanged = {
                shuffleType = ShuffleType.SHUFFLE_FOLDERS
            },
        )
        FotoRadioButton(
            "Only shuffle the images in the folders",
            selected = shuffleType == ShuffleType.SHUFFLE_IMAGES_IN_FOLDERS,
            enabled = subfolders,
            horizontalArrangement = Arrangement.Start,
            onRadioChanged = {
                shuffleType = ShuffleType.SHUFFLE_IMAGES_IN_FOLDERS
            },
        )
        FotoRadioButton(
            "Shuffle the images and folders, but keep the folder structure",
            selected = shuffleType == ShuffleType.SHUFFLE_ALL_KEEPING_GROUPING,
            enabled = subfolders,
            horizontalArrangement = Arrangement.Start,
            onRadioChanged = {
                shuffleType = ShuffleType.SHUFFLE_ALL_KEEPING_GROUPING
            },
        )
    }
}
