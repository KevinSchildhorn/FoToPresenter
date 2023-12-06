package com.kevinschildhorn.fotopresenter.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kevinschildhorn.fotopresenter.ui.compose.common.ErrorView
import com.kevinschildhorn.fotopresenter.ui.compose.common.LoadingOverlay

sealed class State<out DATA> {
    data object IDLE : State<Nothing>()
    data object LOADING : State<Nothing>()
    data class ERROR(
        val message:String,
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

    @Composable
    fun asComposable(modifier: Modifier = Modifier) {
        when (this) {
            is LOADING -> LoadingOverlay()
            is ERROR -> ErrorView(message, modifier = modifier)
            else -> {}
        }
    }
}