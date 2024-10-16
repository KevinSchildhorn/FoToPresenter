package com.kevinschildhorn.fotopresenter.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.ErrorView
import com.kevinschildhorn.fotopresenter.ui.screens.common.composables.LoadingOverlay

sealed class State<out DATA> {
    data object IDLE : State<Nothing>()

    data object LOADING : State<Nothing>()

    data class ERROR(
        val message: String,
    ) : State<Nothing>()

    data class SUCCESS<DATA>(
        val data: DATA,
    ) : State<DATA>()

    inline fun onSuccess(block: (DATA) -> Unit): State<DATA> {
        if (this is SUCCESS) block(data)
        return this
    }

    inline fun onError(block: (String) -> Unit): State<DATA> {
        if (this is ERROR) block(message)
        return this
    }

    inline fun onLoading(block: () -> Unit): State<DATA> {
        if (this is LOADING) block()
        return this
    }

    val value: DATA?
        get() = (this as? SUCCESS)?.data

    @Composable
    fun asComposable(modifier: Modifier = Modifier) {
        when (this) {
            is LOADING -> LoadingOverlay()
            is ERROR -> ErrorView(message, modifier = modifier)
            else -> {}
        }
    }

    override fun toString(): String =
        """
            State:${
            when (this) {
                is IDLE -> "Idle"
                is LOADING -> "Loading"
                is ERROR -> "Error: $message"
                is SUCCESS -> "Success:$data"
            }
        }
        """.trimIndent()
}
