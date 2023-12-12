package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowLeft
import compose.icons.evaicons.fill.ArrowRight
import compose.icons.evaicons.fill.Close

@Composable
fun SlideshowScreen(
    viewModel: SlideshowViewModel,
    onDismiss: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val imageState by viewModel.imageUiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {



        imageState.selectedImage?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        } ?: kotlin.run {
            LoadingOverlay()
        }

        Overlay(5f, modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Button(onClick = onDismiss) {
                        Icon(EvaIcons.Fill.Close, contentDescription = null)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(onClick = {
                        viewModel.skipBackwards()
                    }) {
                        Icon(EvaIcons.Fill.ArrowLeft, contentDescription = null)
                    }
                    Button(onClick = {
                        viewModel.skipForward()
                    }) {
                        Icon(EvaIcons.Fill.ArrowRight, contentDescription = null)
                    }
                }
            }

        }
    }
}
