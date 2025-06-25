package com.kevinschildhorn.fotopresenter.ui.screens.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kevinschildhorn.fotopresenter.data.dropdownMonths
import com.kevinschildhorn.fotopresenter.data.dropdownYears
import com.kevinschildhorn.fotopresenter.ui.TagSearchType
import com.kevinschildhorn.fotopresenter.ui.TestTags
import com.kevinschildhorn.fotopresenter.ui.atoms.disabled
import androidx.compose.material.MaterialTheme
import com.kevinschildhorn.fotopresenter.ui.composables.FotoCheckbox
import com.kevinschildhorn.fotopresenter.ui.composables.FotoDropdown
import com.kevinschildhorn.fotopresenter.ui.composables.FotoRadioButton
import com.kevinschildhorn.fotopresenter.ui.testTag
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Close
import compose.icons.evaicons.outline.PlusCircle
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun AdvancedSearchDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (List<String>, TagSearchType, Boolean, LocalDate?, LocalDate?) -> Unit,
) {
    val selectedOption = remember { mutableStateOf(TagSearchType.ALL_TAGS) }
    val recursive = remember { mutableStateOf(true) }
    val tags = remember { mutableStateListOf("") }

    val currentYear = Clock.System.todayIn(TimeZone.currentSystemDefault()).year

    val dateEnabled = remember { mutableStateOf(false) }
    val startMonth = remember { mutableStateOf("January") }
    val startYear = remember { mutableStateOf(currentYear) }
    val endMonth = remember { mutableStateOf("January") }
    val endYear = remember { mutableStateOf(currentYear) }

    Box {
        FotoDialog(
            "Tag Search",
            onDismissRequest = onDismissRequest,
            onConfirmation = {
                val startDate = if(!dateEnabled.value) null else
                    LocalDate(startYear.value, dropdownMonths.indexOf(startMonth.value), 1)
                val endDate = if (!dateEnabled.value) null else
                    LocalDate(endYear.value, dropdownMonths.indexOf(endMonth.value), 1)

                onConfirmation(
                    tags.toList(),
                    selectedOption.value,
                    recursive.value,
                    startDate,
                    endDate
                )
            },
        ) {
            LazyColumn {
                itemsIndexed(tags) { index, tag ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(IntrinsicSize.Min)
                    ) {
                        OutlinedTextField(
                            value = tag,
                            onValueChange = { newValue ->
                                tags[index] = newValue
                            },
                            label = { Text("Tag") },
                            modifier = Modifier.weight(1f),
                        )
                        IconButton(
                            onClick = {
                                if (index == tags.size - 1) {
                                    tags.add("")
                                } else {
                                    tags.removeAt(index)
                                }
                            },
                            modifier = Modifier.padding(top = 8.dp).width(44.dp)
                        ) {
                            val imageVector =
                                if (index == tags.size - 1) EvaIcons.Outline.PlusCircle
                                else EvaIcons.Outline.Close
                            Icon(
                                imageVector = imageVector,
                                contentDescription = "Add Tag",
                                tint = MaterialTheme.colors.onSurface,
                                modifier = Modifier.fillMaxHeight().width(30.dp)
                            )
                        }
                    }
                }
                item {
                    FotoRadioButton(
                        title = "All of the Tags",
                        selected = selectedOption.value == TagSearchType.ALL_TAGS,
                        onRadioChanged = { selectedOption.value = TagSearchType.ALL_TAGS },
                        modifier = Modifier.testTag(TestTags.Directory.TagSearch.ALL_TAGS),
                        horizontalArrangement = Arrangement.Start,
                    )
                }
                item {
                    FotoRadioButton(
                        title = "Any of the Tags",
                        selected = selectedOption.value == TagSearchType.ANY_TAGS,
                        onRadioChanged = { selectedOption.value = TagSearchType.ANY_TAGS },
                        modifier = Modifier.testTag(TestTags.Directory.TagSearch.ANY_TAGS),
                        horizontalArrangement = Arrangement.Start,
                    )
                }
                item {
                    FotoCheckbox(
                        title = "Search Subfolders",
                        checked = recursive.value,
                        onCheckedChange = { recursive.value = it },
                        modifier = Modifier.testTag(TestTags.Directory.TagSearch.RECURSIVE),
                        horizontalArrangement = Arrangement.Start,
                    )
                }
                item {
                    FotoCheckbox(
                        title = "Filter by Date",
                        checked = dateEnabled.value,
                        onCheckedChange = { dateEnabled.value = it },
                        modifier = Modifier.testTag(TestTags.Directory.TagSearch.DATE),
                        horizontalArrangement = Arrangement.Start,
                    )
                }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Start",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End,
                            color = if (dateEnabled.value) MaterialTheme.colors.onSurface else disabled
                        )
                        FotoDropdown(
                            dropdownMonths,
                            label = "Month",
                            enabled = dateEnabled.value,
                            modifier = Modifier.weight(2f)
                        ) {
                            startMonth.value = it
                        }
                        FotoDropdown(
                            dropdownYears,
                            label = "Year",
                            enabled = dateEnabled.value,
                            modifier = Modifier.weight(2f)
                        ) {
                            startYear.value = it
                        }
                    }
                }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "End",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End,
                            color = if (dateEnabled.value) MaterialTheme.colors.onSurface else disabled
                        )
                        FotoDropdown(
                            dropdownMonths, label = "Month",
                            enabled = dateEnabled.value, modifier = Modifier.weight(2f)
                        ) {
                            endMonth.value = it
                        }
                        FotoDropdown(
                            dropdownYears, label = "Year",
                            enabled = dateEnabled.value, modifier = Modifier.weight(2f)
                        ) {
                            endYear.value = it
                        }
                    }
                }
            }
        }
    }
}