package com.mctech.architecture.feature.feature_empty

import android.os.Bundle
import android.view.View
import com.mctech.samplesample_architecture.BaseFragment
import com.mctech.samplesample_architecture.ViewCommand
import com.mctech.samplesample_architecture.extentions.bindCommand
import com.mctech.samplesample_architecture.extentions.sharedViewModel

class FeatureEmptyFragment : BaseFragment() {
	private val viewModel : FeatureEmptyViewModel by sharedViewModel(FeatureEmptyViewModel::class.java)

	override fun getLayoutId() = R.layout.fragment_feature_empty

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindCommand(viewModel){ handleCommand(it) }

	}

	private fun handleCommand(it: ViewCommand) {
		when(it){

		}
	}

}
