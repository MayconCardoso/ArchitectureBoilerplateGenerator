package com.mctech.architecture.feature.complex_feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.mvvm.x.core.ComponentState
import com.mctech.architecture.mvvm.x.core.ViewCommand
import com.mctech.architecture.mvvm.x.core.ktx.bindCommand
import com.mctech.architecture.mvvm.x.core.ktx.bindData
import com.mctech.architecture.mvvm.x.core.ktx.bindState

class ComplexFeatureActivity : AppCompatActivity() {
	private val viewModel : ComplexFeatureViewModel by daggerViewModel(ComplexFeatureViewModel::class.java)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_complex_feature)

		bindCommand(viewModel){ handleCommand(it) }

		bindData(viewModel.items){ handleItemsData(it) }
		bindData(viewModel.userName){ handleUserNameData(it) }
		bindState(viewModel.listEntities){ handleListEntitiesState(it) }
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

	private fun handleListEntitiesState(state: ComponentState<List<ComplexFeature>>) {
		when(state){
			is ComponentState.Initializing -> TODO()
			is ComponentState.Loading -> TODO()
			is ComponentState.Error -> TODO()
			is ComponentState.Success -> TODO()
		}
	}

}
