package com.kevinschildhorn.fotopresenter.ui.compose.directory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.data.ImageDirectoryContent
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors
import com.kevinschildhorn.fotopresenter.ui.state.State
import com.kevinschildhorn.fotopresenter.ui.viewmodel.PhotoDirectoryViewModel
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.QuestionMark

@Composable
fun PhotoDirectoryItem(
    imageContent: ImageDirectoryContent,
    modifier: Modifier = Modifier,
    viewModel: PhotoDirectoryViewModel = PhotoDirectoryViewModel(imageContent.image),
) {
    val imageState by viewModel.imageState.collectAsState(State.IDLE)

    LaunchedEffect(Unit) {
        viewModel.refreshImageBitmap()
    }
    BaseDirectory(modifier) {
        imageState.onSuccess {
            Image(
                bitmap = it,
                contentDescription = "Image",
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
                DirectoryText(
                    imageContent.directory.name,
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
