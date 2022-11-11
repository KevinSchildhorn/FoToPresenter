package me.kevinschildhorn.common.layers.ui.viewmodel.base

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import me.kevinschildhorn.common.FlowAdapter
import me.kevinschildhorn.common.MutableStateFlowAdapter
import me.kevinschildhorn.common.StateFlowAdapter
import me.kevinschildhorn.common.layers.ui.viewmodel.base.ViewModel

/**
 * Base class that provides a Kotlin/Native equivalent to the AndroidX `ViewModel`. In particular, this provides
 * a [CoroutineScope][kotlinx.coroutines.CoroutineScope] which uses [Dispatchers.Main][kotlinx.coroutines.Dispatchers.Main]
 * and can be tied into an arbitrary lifecycle by calling [clear] at the appropriate time.
 */
actual abstract class ViewModel {

    actual val viewModelScope = MainScope()

    /**
     * Override this to do any cleanup immediately before the internal [CoroutineScope][kotlinx.coroutines.CoroutineScope]
     * is cancelled in [clear]
     */
    protected actual open fun onCleared() {
    }

    /**
     * Cancels the internal [CoroutineScope][kotlinx.coroutines.CoroutineScope]. After this is called, the ViewModel should
     * no longer be used.
     */
    internal fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}

abstract class CallbackViewModel {
    protected abstract val viewModel: ViewModel

    /**
     * Create a [FlowAdapter] from this [Flow] to make it easier to interact with from Swift.
     */
    fun <T : Any> Flow<T>.asCallbacks() =
        FlowAdapter(viewModel.viewModelScope, this)

    /**
     * Create a [StateFlowAdapter] from this [StateFlow] to make it easier to interact with from Swift.
     */
    fun <T : Any> StateFlow<T>.asCallbacks() =
        StateFlowAdapter(viewModel.viewModelScope, this)

    fun <T : Any> MutableStateFlow<T>.asCallbacks() =
        MutableStateFlowAdapter(viewModel.viewModelScope, this)

    @Suppress("Unused") // Called from Swift
    fun clear() = viewModel.clear()
}