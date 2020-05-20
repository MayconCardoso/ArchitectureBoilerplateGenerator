package com.mctech.architecture.data.complex_feature.datasource

import com.mctech.architecture.data.complex_feature.api.ComplexFeatureAPI
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.feature_empty.entity.FeatureEmpty

class RemoteComplexFeatureDataSource(private val api : ComplexFeatureAPI) : ComplexFeatureDataSource{

	override suspend fun loadAllItems(): List<ComplexFeature>{
		TODO()
	}

	override suspend fun loadItemDetail(item: ComplexFeature, simpleList: FeatureEmpty): ComplexFeature{
		TODO()
	}

}
