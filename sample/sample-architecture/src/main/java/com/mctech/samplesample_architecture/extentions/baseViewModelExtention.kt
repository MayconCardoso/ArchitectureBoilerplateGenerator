package com.mctech.samplesample_architecture.extentions

import androidx.lifecycle.ViewModel
import com.mctech.samplesample_architecture.BaseActivity
import com.mctech.samplesample_architecture.BaseFragment

fun <VM> BaseFragment.sharedViewModel(viewModelClass: Class<out ViewModel>) = lazy {
    activity?.let { it ->
        androidx.lifecycle.ViewModelProviders.of(it,
            activity.takeIf { it is BaseActivity }?.let {
                (it as BaseActivity).viewModelFactory
            }
        ).get(viewModelClass) as VM
    } ?: throw IllegalArgumentException()
}


fun <VM> BaseActivity.daggerViewModel(viewModelClass: Class<out ViewModel>) = lazy {
    androidx.lifecycle.ViewModelProviders.of(this, viewModelFactory).get(viewModelClass) as VM
}