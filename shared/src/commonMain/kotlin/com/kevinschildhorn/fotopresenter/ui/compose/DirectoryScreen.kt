package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.data.network.MockNetworkDirectory
import com.kevinschildhorn.fotopresenter.ui.compose.common.LoadingOverlay
import com.kevinschildhorn.fotopresenter.ui.compose.directory.DirectoryGrid
import com.kevinschildhorn.fotopresenter.ui.viewmodel.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel

@Composable
fun DirectoryScreen(
    viewModel: DirectoryViewModel,
) {
    LaunchedEffect(Unit){
        viewModel.refreshScreen()
    }
    val uiState by viewModel.uiState.collectAsState()
    if(uiState.state.isLoading) {
        LoadingOverlay()
    }

    val docs = listOf(
        MockNetworkDirectory("", id = 0),
        MockNetworkDirectory("", id = 1),
        MockNetworkDirectory("", id = 2),
        MockNetworkDirectory("", id = 3),
        MockNetworkDirectory("", id = 4),
        MockNetworkDirectory("", id = 5),
        MockNetworkDirectory("", id = 6),
        MockNetworkDirectory("", id = 7),
        MockNetworkDirectory("", id = 8),
        MockNetworkDirectory("", id = 9),
        MockNetworkDirectory("", id = 10),
        MockNetworkDirectory("", id = 20),
        MockNetworkDirectory("", id = 30),
        MockNetworkDirectory("", id = 40),
        MockNetworkDirectory("", id = 50),
        MockNetworkDirectory("", id = 60),
        MockNetworkDirectory("", id = 70),
        MockNetworkDirectory("", id = 80),
        MockNetworkDirectory("", id = 90),
        MockNetworkDirectory("", id = 100),
        MockNetworkDirectory("", id = 110),
        MockNetworkDirectory("", id = 120),
        MockNetworkDirectory("", id = 130),
        MockNetworkDirectory("", id = 140),
        MockNetworkDirectory("", id = 150),
        MockNetworkDirectory("", id = 160),
        MockNetworkDirectory("", id = 170),
        MockNetworkDirectory("", id = 180),
        MockNetworkDirectory("", id = 190),
        MockNetworkDirectory("", id = 200),
        MockNetworkDirectory("", id = 210),
        MockNetworkDirectory("", id = 220),
        MockNetworkDirectory("", id = 230),
        MockNetworkDirectory("", id = 240),
        MockNetworkDirectory("", id = 250),
        MockNetworkDirectory("", id = 260),
        MockNetworkDirectory("", id = 270),
        MockNetworkDirectory("", id = 280),
        MockNetworkDirectory("", id = 290),
        MockNetworkDirectory("", id = 300),
        MockNetworkDirectory("", id = 310),
        MockNetworkDirectory("", id = 320),
        MockNetworkDirectory("", id = 330),
        MockNetworkDirectory("", id = 340),
        MockNetworkDirectory("", id = 350),
        MockNetworkDirectory("", id = 21),
        MockNetworkDirectory("", id = 22),
        MockNetworkDirectory("", id = 23),
        MockNetworkDirectory("", id = 24),
        MockNetworkDirectory("", id = 25),
        MockNetworkDirectory("", id = 26),
        MockNetworkDirectory("", id = 27),
        MockNetworkDirectory("", id = 28),
        MockNetworkDirectory("", id = 29),
    )
    DirectoryGrid(docs/*uiState.directories*/)
}
