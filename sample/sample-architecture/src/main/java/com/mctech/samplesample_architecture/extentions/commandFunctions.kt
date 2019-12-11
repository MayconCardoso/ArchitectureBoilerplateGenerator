package com.mctech.samplesample_architecture.extentions
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mctech.samplesample_architecture.BaseViewModel
import com.mctech.samplesample_architecture.SingleLiveEvent
import com.mctech.samplesample_architecture.ViewCommand


fun autoDisposeCommandObserver(lifecycle: LifecycleOwner, viewModel: BaseViewModel, block: (result: ViewCommand) -> Unit) {
    val key = lifecycle::class.java.simpleName
    val commandObservable = ((viewModel.commandObservable) as SingleLiveEvent<ViewCommand>)
    commandObservable.observe(
            key,
            lifecycle,
            Observer {
                block(it)
                commandObservable.removeObserver(key)
            }
    )
}

fun commandObserver(lifecycle: LifecycleOwner, viewModel: BaseViewModel, block: (result: ViewCommand) -> Unit) {
    ((viewModel.commandObservable) as SingleLiveEvent<ViewCommand>).observe(
            lifecycle::class.java.simpleName,
            lifecycle,
            Observer {
                block(it)
            }
    )
}