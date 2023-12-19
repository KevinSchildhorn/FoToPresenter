package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.grid

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.screens.directory.ImageDirectoryGridCellState
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.QuestionMark

@Composable
fun ImageDirectoryGridCell(
    imageContent: ImageDirectoryGridCellState,
    modifier: Modifier = Modifier,
) {
    DirectoryGridCell(modifier) {
        imageContent.imageState.onSuccess {
            Image(
                bitmap = it,
                contentDescription = imageContent.name,
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
                    imageContent.name,
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
