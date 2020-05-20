package com.mctech.architecture.feature.complex_feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mctech.architecture.domain.Result
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.interaction.LoadAllItemsCase
import com.mctech.architecture.domain.complex_feature.interaction.LoadItemDetailCase
import com.mctech.architecture.mvvm.x.core.BaseViewModel
import com.mctech.architecture.mvvm.x.core.ComponentState
import com.mctech.architecture.mvvm.x.core.OnInteraction
import com.mctech.architecture.mvvm.x.core.ktx.changeToErrorState
import com.mctech.architecture.mvvm.x.core.ktx.changeToListLoadingState
import com.mctech.architecture.mvvm.x.core.ktx.changeToLoadingState
import com.mctech.architecture.mvvm.x.core.ktx.changeToSuccessState

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

	private val _itemDetails : MutableLiveData<ComponentState<ComplexFeature>> = MutableLiveData(ComponentState.Initializing)
	val itemDetails : LiveData<ComponentState<ComplexFeature>> = _itemDetails

	@OnInteraction(ComplexFeatureUserInteraction.LoadList::class)
	private suspend fun loadListInteraction(){
		_listEntities.changeToListLoadingState()

		when(val result = loadAllItemsCase.execute()){
			is Result.Success -> {
				_listEntities.changeToSuccessState(result.result)
			}
			is Result.Failure -> {
				_listEntities.changeToErrorState(result.throwable)
			}
		}

	}

	@OnInteraction(ComplexFeatureUserInteraction.OpenDetails::class)
	private suspend fun openDetailsInteraction(interaction : ComplexFeatureUserInteraction.OpenDetails){
		_itemDetails.changeToLoadingState()

		when(val result = loadItemDetailCase.execute(interaction.item, interaction.simpleList)){
			is Result.Success -> {
				_itemDetails.changeToSuccessState(result.result)
			}
			is Result.Failure -> {
				_itemDetails.changeToErrorState(result.throwable)
			}
		}

	}

}
