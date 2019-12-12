package com.mctech.architecture.feature.feature_empty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import com.mctech.samplesample_architecture.BaseViewModel

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.interaction.LoadAllItemsCase
import com.mctech.architecture.domain.complex_feature.interaction.LoadItemDetailCase

class ComplexFeatureViewModel @Inject constructor(
	private val loadAllItemsCase: LoadAllItemsCase,
	private val loadItemDetailCase: LoadItemDetailCase
) : BaseViewModel() {

	private val _items = MutableLiveData<List<ComplexFeature>>()
	val items : LiveData<List<ComplexFeature>> = _items

	private val _userName = MutableLiveData<String>()
	val userName : LiveData<String> = _userName

}
