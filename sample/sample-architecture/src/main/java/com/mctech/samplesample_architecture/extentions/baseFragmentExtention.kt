package com.mctech.samplesample_architecture.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mctech.samplesample_architecture.BaseViewModel
import com.mctech.samplesample_architecture.ComponentState
import com.mctech.samplesample_architecture.ViewCommand
import kotlinx.coroutines.launch

fun <T> Fragment.bindData(observable: LiveData<T>, block: (result: T) -> Unit) {
    lifecycleScope.launch {
        observable.observe(this@bindData, Observer {
            block(it)
        })
    }
}

fun <T> Fragment.bindState(observable: LiveData<ComponentState<T>>, block: (result: ComponentState<T>) -> Unit) = bindData(observable, block)

fun Fragment.bindCommand(viewModel: BaseViewModel, block: (result: ViewCommand) -> Unit) {
    lifecycleScope.launch { commandObserver(
            lifecycle   = this@bindCommand,
            viewModel   = viewModel,
            block       = block
    )}
}

fun Fragment.bindAutoDisposableCommand(viewModel: BaseViewModel, block: (result: ViewCommand) -> Unit) {
    lifecycleScope.launch { autoDisposeCommandObserver(
            lifecycle   = this@bindAutoDisposableCommand,
            viewModel   = viewModel,
            block       = block
    )}
}
