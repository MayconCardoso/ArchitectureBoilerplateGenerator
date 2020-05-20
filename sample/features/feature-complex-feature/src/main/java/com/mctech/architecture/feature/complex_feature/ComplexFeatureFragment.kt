package com.mctech.architecture.feature.complex_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.mvvm.x.core.ComponentState
import com.mctech.architecture.mvvm.x.core.ViewCommand
import com.mctech.architecture.mvvm.x.core.ktx.bindCommand
import com.mctech.architecture.mvvm.x.core.ktx.bindData
import com.mctech.architecture.mvvm.x.core.ktx.bindState

class ComplexFeatureFragment : Fragment() {
	private val viewModel : ComplexFeatureViewModel by sharedViewModel(ComplexFeatureViewModel::class.java)

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_complex_feature, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindCommand(viewModel){ handleCommand(it) }

		bindData(viewModel.items){ handleItemsData(it) }
		bindData(viewModel.userName){ handleUserNameData(it) }
		bindState(viewModel.listEntities){ handleListEntitiesState(it) }
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

	private fun handleListEntitiesState(state: ComponentState<List<ComplexFeature>>) {
		when(state){
			is ComponentState.Initializing -> TODO()
			is ComponentState.Loading -> TODO()
			is ComponentState.Error -> TODO()
			is ComponentState.Success -> TODO()
		}
	}

}
