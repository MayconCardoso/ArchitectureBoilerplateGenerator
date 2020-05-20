package com.mctech.architecture.feature.complex_feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.interaction.LoadAllItemsCase
import com.mctech.architecture.domain.complex_feature.interaction.LoadItemDetailCase
import com.mctech.architecture.mvvm.x.core.BaseViewModel
import com.mctech.architecture.mvvm.x.core.ComponentState
import com.mctech.architecture.mvvm.x.core.OnInteraction

class ComplexFeatureViewModel (
	private val loadAllItemsCase: LoadAllItemsCase,
	private val loadItemDetailCase: LoadItemDetailCase
) : BaseViewModel() {

	private val _items = MutableLiveData<List<ComplexFeature>>()
	val items : LiveData<List<ComplexFeature>> = _items

	private val _userName = MutableLiveData<String>()
	val userName : LiveData<String> = _userName

	private val _listEntities : MutableLiveData<ComponentState<List<ComplexFeature>>> = MutableLiveData(ComponentState.Initializing)
	val listEntities : LiveData<ComponentState<List<ComplexFeature>>> = _listEntities

	@OnInteraction(ComplexFeatureUserInteraction.LoadList::class)
	private suspend fun loadListInteraction(){

	}

	@OnInteraction(ComplexFeatureUserInteraction.OpenDetails::class)
	private suspend fun openDetailsInteraction(interaction : ComplexFeatureUserInteraction.OpenDetails){

	}

}
