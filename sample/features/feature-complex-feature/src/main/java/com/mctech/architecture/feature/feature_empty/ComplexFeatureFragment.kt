package com.mctech.architecture.feature.feature_empty

import android.os.Bundle
import android.view.View

import com.mctech.samplesample_architecture.BaseFragment
import com.mctech.samplesample_architecture.ComponentState
import com.mctech.samplesample_architecture.ViewCommand
import com.mctech.samplesample_architecture.extentions.bindState
import com.mctech.samplesample_architecture.extentions.bindData
import com.mctech.samplesample_architecture.extentions.bindCommand
import com.mctech.samplesample_architecture.extentions.sharedViewModel
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature

class ComplexFeatureFragment : BaseFragment() {
	private val viewModel : ComplexFeatureViewModel by sharedViewModel(ComplexFeatureViewModel::class.java)

	override fun getLayoutId() = R.layout.fragment_complex_feature

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindCommand(viewModel){ handleCommand(it) }

		bindData(viewModel.items){ handleItemsData(it) }
		bindData(viewModel.userName){ handleUserNameData(it) }
	}

	private fun handleCommand(it: ViewCommand) {
		when(it){

		}
	}

	private fun handleItemsData(it: List<ComplexFeature>) {
		TODO()
	}

	private fun handleUserNameData(it: String) {
		TODO()
	}

}
