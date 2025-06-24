package com.kevinschildhorn.fotopresenter.ui.screens.slideshow

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.Overlay
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.ArrowLeft
import compose.icons.evaicons.fill.ArrowRight
import compose.icons.evaicons.fill.Close
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideshowScreen(
    viewModel: SlideshowViewModel,
    onDismiss: () -> Unit,
) {
    val imageState by viewModel.uiState.collectAsState()
    var show by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000L)
            show = false
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        val details = imageState
        val platformContext = LocalPlatformContext.current
        when (details) {
            is SlideshowScreenUiState.Loading -> LoadingOverlay()
            is SlideshowScreenUiState.Ready -> {
                    AsyncImage(
                        model = ImageRequest.Builder(platformContext)
                            .data(details.selectedImageDirectory.model)
                            .crossfade(true)
                            .crossfade(500)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                    )
            }
        }
    }

    Overlay(
        5f,
        visible = true,
        onDismiss = {},
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(44.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                AnimatedVisibility(
                    visible = show,
                    enter = fadeIn(animationSpec = tween(500)),
                    exit = fadeOut(animationSpec = tween(500)),
                ) {
                    TextButton(onClick = {
                        viewModel.stopSlideshow()
                        onDismiss()
                    }) {
                        Icon(
                            EvaIcons.Fill.Close,
                            tint = Color.White,
                            contentDescription = "Close",
                            modifier = Modifier.size(55.dp),
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.5f)
                            .combinedClickable(
                                onClick = {
                                    show = true
                                },
                                onDoubleClick = {
                                    viewModel.skipBackwards()
                                },
                            ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        if (show) {
                            Icon(
                                EvaIcons.Fill.ArrowLeft,
                                tint = Color.White,
                                contentDescription = "Left",
                                modifier = Modifier.size(55.dp),
                            )
                        }
                    }
                }
                Box(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    show = true
                                },
                                onDoubleClick = {
                                    viewModel.skipForward()
                                },
                            ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        if (show) {
                            Icon(
                                EvaIcons.Fill.ArrowRight,
                                tint = Color.White,
                                contentDescription = "Right",
                                modifier = Modifier.size(55.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
