package com.kevinschildhorn.fotopresenter.ui.compose.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.atoms.Padding

@Composable
inline fun FormColumn(
    modifier: Modifier = Modifier,
    verticalPadding: Padding = Padding.STANDARD,
    content: @Composable ColumnScope.() -> Unit
) {
    val halfPadding:Int = verticalPadding.rawValue / 2
    Column(
        modifier
            .fillMaxWidth()
            .padding(vertical = halfPadding.dp),
        verticalArrangement = Arrangement.spacedBy(verticalPadding.dp),
        content = content,
    )
}