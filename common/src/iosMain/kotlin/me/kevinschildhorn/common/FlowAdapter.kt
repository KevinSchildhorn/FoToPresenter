package me.kevinschildhorn.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

@Suppress("unused")
open class FlowAdapter<T : Any>(
    private val scope: CoroutineScope,
    private val flow: Flow<T>,
) {
    fun subscribe(
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit,
    ): Canceller = JobCanceller(
        flow.onEach { onEach(it) }
            .catch { onThrow(it) }
            .onCompletion { onComplete() }
            .launchIn(scope)
    )

    fun subscribe(
        onEach: (item: T) -> Unit
    ): Canceller = JobCanceller(
        flow.onEach { onEach(it) }
            .launchIn(scope)
    )
}

open class StateFlowAdapter<T : Any>(
    scope: CoroutineScope,
    private val flow: StateFlow<T>,
) : FlowAdapter<T>(scope, flow) {
    open val value: T
        get() = flow.value
}

open class MutableStateFlowAdapter<T : Any>(
    scope: CoroutineScope,
    private val flow: MutableStateFlow<T>,
) : FlowAdapter<T>(scope, flow) {
    open var value: T
        get() = flow.value
        set(value) {
            flow.value = value
        }
}

interface Canceller {
    fun cancel()
}

private class JobCanceller(private val job: Job) : Canceller {
    override fun cancel() {
        job.cancel()
    }
}
