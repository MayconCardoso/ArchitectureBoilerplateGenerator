package com.mctech.architecture.feature.feature_empty

import android.os.Bundle

import com.mctech.samplesample_architecture.BaseActivity
import com.mctech.samplesample_architecture.ComponentState
import com.mctech.samplesample_architecture.ViewCommand
import com.mctech.samplesample_architecture.extentions.bindState
import com.mctech.samplesample_architecture.extentions.bindData
import com.mctech.samplesample_architecture.extentions.bindCommand
import com.mctech.samplesample_architecture.extentions.daggerViewModel
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature

class ComplexFeatureActivity : BaseActivity() {
	private val viewModel : ComplexFeatureViewModel by daggerViewModel(ComplexFeatureViewModel::class.java)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_complex_feature)

		bindCommand(viewModel){ handleCommand(it) }

		bindData(viewModel.items){ handleItemsData(it) }
		bindData(viewModel.userName){ handleUserNameData(it) }
	}

	private fun handleCommand(it: ViewCommand) {
		when(it){
			//TODO()
		}
	}

	private fun handleItemsData(it: List<ComplexFeature>) {
		TODO()
	}

	private fun handleUserNameData(it: String) {
		TODO()
	}

}
