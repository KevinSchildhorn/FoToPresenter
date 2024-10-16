package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables.navbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.atoms.disabled
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Home

@Composable
fun DirectoryNavigationHome(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(44.dp).width(44.dp).clip(RoundedCornerShape(10.dp)),
        colors =
            ButtonDefaults.buttonColors(
                backgroundColor = fotoColors.secondary,
                disabledBackgroundColor = disabled,
            ),
    ) {
        Icon(
            imageVector = EvaIcons.Fill.Home,
            tint = fotoColors.onSecondary,
            contentDescription = "Home",
            modifier = Modifier.size(44.dp),
        )
    }
}
