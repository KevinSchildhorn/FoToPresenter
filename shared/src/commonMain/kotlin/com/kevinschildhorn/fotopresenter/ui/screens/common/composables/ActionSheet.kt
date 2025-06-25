package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.TestTags.ACTION_SHEET
import com.kevinschildhorn.fotopresenter.ui.TestTags.ACTION_SHEET_ITEM
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.testTag

@Composable
fun ActionSheet(
    // TODO: "Visible" Not always being used
    visible: Boolean,
    offset: Int,
    values: List<ActionSheetContext>,
    onClick: (ActionSheetContext) -> Unit,
    onDismiss: () -> Unit,
) {
    Overlay(
        3f,
        visible = visible,
        onDismiss = onDismiss,
        enter =
            slideInVertically(
                initialOffsetY = { offset },
            ),
        exit =
            slideOutVertically(
                targetOffsetY = { offset },
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().testTag(ACTION_SHEET),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Spacer(modifier = Modifier.fillMaxWidth())
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .weight(1f, false)
                        .background(MaterialTheme.colors.secondary),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(vertical = 10.dp),
                ) {
                    items(values) {
                        TextButton(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(start = 10.dp)
                                    .testTag(ACTION_SHEET_ITEM(it.action)),
                            onClick = {
                                onClick(it)
                            },
                        ) {
                            Text(
                                it.action.title,
                                color = MaterialTheme.colors.onSecondary,
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.h4,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}
