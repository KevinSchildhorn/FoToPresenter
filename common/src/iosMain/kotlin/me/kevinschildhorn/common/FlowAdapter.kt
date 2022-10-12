package me.kevinschildhorn.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*


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