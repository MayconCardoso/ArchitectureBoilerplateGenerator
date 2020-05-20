package com.mctech.architecture.feature.feature_empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mctech.architecture.mvvm.x.core.ViewCommand
import com.mctech.architecture.mvvm.x.core.ktx.bindCommand

class FeatureEmptyFragment : Fragment() {
	private val viewModel : FeatureEmptyViewModel by sharedViewModel(FeatureEmptyViewModel::class.java)

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_feature_empty, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		bindCommand(viewModel){ handleCommand(it) }

	}

	private fun handleCommand(it: ViewCommand) {
		when(it){

		}
	}

}
