package com.mctech.architecture.feature.feature_empty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mctech.architecture.mvvm.x.core.ViewCommand
import com.mctech.architecture.mvvm.x.core.ktx.bindCommand

class FeatureEmptyActivity : AppCompatActivity() {
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
