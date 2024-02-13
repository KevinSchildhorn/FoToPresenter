package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.UseCaseFactory
import com.kevinschildhorn.fotopresenter.data.ImageDirectory
import com.kevinschildhorn.fotopresenter.data.State
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.QuestionMark
import kotlinx.coroutines.launch

@Composable
fun ImageDirectoryAsyncGridCell(
    directory: ImageDirectory,
    modifier: Modifier = Modifier,
) {

    val useCase = UseCaseFactory.retrieveImageAsyncUseCase
    var state: State<ImageBitmap> by remember { mutableStateOf(State.IDLE) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        print("Hello Dolly!")
        coroutineScope.launch {
            useCase(directory, imageSize = 512) {
                state = it
            }
        }
    }

    DirectoryGridCell(modifier) {
        state
            .onSuccess {
                Image(
                    bitmap = it,
                    contentDescription = directory.details.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().background(FotoColors.surface.composeColor),
                )
            }.onError {
                Column(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                ) {
                    Image(
                        imageVector = EvaIcons.Fill.QuestionMark,
                        contentDescription = "Question Mark",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    DirectoryGridCellText(
                        directory.details.name,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                }
            }.onLoading {
                CircularProgressIndicator(
                    modifier = Modifier.width(33.dp).align(Alignment.Center),
                    color = FotoColors.primary.composeColor,
                )
            }
    }
}
