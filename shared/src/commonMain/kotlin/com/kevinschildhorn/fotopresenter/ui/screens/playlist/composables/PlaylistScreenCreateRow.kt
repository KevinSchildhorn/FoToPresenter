package com.kevinschildhorn.fotopresenter.ui.screens.playlist.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.atomik.atomic.atoms.textStyle
import com.kevinschildhorn.atomik.color.base.composeColor
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.AtomikIcon
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.AtomikText
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistScreenAtoms
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.PlusCircle

@Composable
fun PlaylistScreenCreateRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val atom = PlaylistScreenAtoms.rowTitle
    Row(
        modifier = modifier.fillMaxWidth().height(55.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick
        ) {
            AtomikIcon(
                EvaIcons.Outline.PlusCircle,
                atom,
                contentDescription = "Create",
            )
            Spacer(Modifier.width(Padding.SMALL.dp))
            AtomikText("Create", atom)
            Spacer(Modifier.fillMaxWidth())
        }
    }
}