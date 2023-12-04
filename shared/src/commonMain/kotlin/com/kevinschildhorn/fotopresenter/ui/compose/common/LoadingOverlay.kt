package com.kevinschildhorn.fotopresenter.ui.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.FotoColors

@Composable
fun LoadingOverlay() {
    Box(
        modifier = Modifier
            .zIndex(2f)
            .fillMaxSize()
            .background(Color(0x88FFFFFF))
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(75.dp).align(Alignment.Center),
            color = FotoColors.primary.composeColor,
        )
    }
}