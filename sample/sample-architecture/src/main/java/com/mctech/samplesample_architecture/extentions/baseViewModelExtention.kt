package com.mctech.samplesample_architecture.extentions

import androidx.lifecycle.ViewModel
import com.mctech.samplesample_architecture.BaseActivity

fun <VM> BaseActivity.daggerViewModel(viewModelClass: Class<out ViewModel>) = kotlin.lazy {
    androidx.lifecycle.ViewModelProviders.of(this, viewModelFactory).get(viewModelClass) as VM
}