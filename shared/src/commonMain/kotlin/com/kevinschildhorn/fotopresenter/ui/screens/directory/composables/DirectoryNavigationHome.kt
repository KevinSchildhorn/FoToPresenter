package com.kevinschildhorn.fotopresenter.ui.screens.directory.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.AtomikIcon
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.AtomikText
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryAtoms
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Home

@Composable
fun DirectoryNavigationHome(onClick: () -> Unit) {
    val molecule = DirectoryAtoms.navigationItem
    Button(
        onClick = onClick,
        modifier = Modifier.height(44.dp).width(44.dp).clip(RoundedCornerShape(molecule.radius.dp)),
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = molecule.color.composeColor,
            disabledBackgroundColor = molecule.disabledColor.composeColor,
        ),
    ) {
        AtomikIcon(
            EvaIcons.Fill.Home,
            atom = molecule.textAtom,
            contentDescription = "Home",
            modifier = Modifier.size(44.dp)
        )
    }
}