package com.kevinschildhorn.fotopresenter

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onNodeWithTag
import com.kevinschildhorn.fotopresenter.ui.TestTag

fun SemanticsNodeInteractionsProvider.onNodeWithTag(
    testTag: TestTag,
    useUnmergedTree: Boolean = false,
) = this.onNodeWithTag(testTag.value, useUnmergedTree)
