package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.ui.TagSearchType
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.atoms.fotoColors
import com.kevinschildhorn.fotopresenter.ui.testTag
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Close
import compose.icons.evaicons.outline.PlusCircle

@Composable
fun AdvancedSearchDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (TagSearchType) -> Unit,
) {
    val selectedOption = remember { mutableStateOf(TagSearchType.ALL_TAGS) }
    val tags = remember { mutableStateOf(mutableListOf("")) }

    FotoDialog(
        "Tag Search",
        onDismissRequest = onDismissRequest,
        onConfirmation = {
            onConfirmation(selectedOption.value)
        },
    ) {
        LazyColumn {
            itemsIndexed(tags.value) { index, tag ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.height(IntrinsicSize.Min)
                ) {
                    OutlinedTextField(
                        value = tag,
                        onValueChange = { newValue ->
                            tags.apply { this[index] = newValue }
                        },
                        label = { Text("Tag") },
                        modifier = Modifier.weight(1f),
                    )
                    IconButton(
                        onClick = {
                            if (index == tags.value.size - 1) {
                                tags.value = tags.apply { add("") }
                            } else {
                                tags.value = tags.apply { removeAt(index) }
                            }
                        },
                        modifier = Modifier.padding(top = 8.dp).width(44.dp)
                    ) {
                        val imageVector =
                            if (index == tags.value.size - 1) EvaIcons.Outline.PlusCircle
                            else EvaIcons.Outline.Close
                        Icon(
                            imageVector = imageVector,
                            contentDescription = "Add Tag",
                            tint = fotoColors.onSurface,
                            modifier = Modifier.fillMaxHeight().width(30.dp)
                        )
                    }
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == TagSearchType.ALL_TAGS,
                onClick = { selectedOption.value = TagSearchType.ALL_TAGS },
                modifier = Modifier.testTag(TestTags.Directory.TagSearch.ALL_TAGS),
            )
            Text(
                text = "All of the Tags",
                style = MaterialTheme.typography.button,
                color = fotoColors.onSurface,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedOption.value == TagSearchType.ANY_TAGS,
                onClick = { selectedOption.value = TagSearchType.ANY_TAGS },
                modifier = Modifier.testTag(TestTags.Directory.TagSearch.ANY_TAGS),
            )
            Text(
                text = "Any of the Tags",
                style = MaterialTheme.typography.button,
                color = fotoColors.onSurface,
            )
        }
    }
}

private fun MutableState<MutableList<String>>.apply(block: MutableList<String>.() -> Unit) =
    this.value.toMutableList().apply(block)