package com.mctech.architecture.feature.feature_empty

import android.os.Bundle
import com.mctech.samplesample_architecture.BaseActivity
import com.mctech.samplesample_architecture.ViewCommand
import com.mctech.samplesample_architecture.extentions.bindCommand
import com.mctech.samplesample_architecture.extentions.daggerViewModel

class FeatureEmptyActivity : BaseActivity() {
	private val viewModel : FeatureEmptyViewModel by daggerViewModel(FeatureEmptyViewModel::class.java)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_feature_empty)

		bindCommand(viewModel){ handleCommand(it) }

	}

	private fun handleCommand(it: ViewCommand) {
		when(it){
			//TODO()
		}
	}

}
