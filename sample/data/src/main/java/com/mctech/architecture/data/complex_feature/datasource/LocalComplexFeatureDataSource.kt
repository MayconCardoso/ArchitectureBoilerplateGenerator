package com.mctech.architecture.data.complex_feature.datasource

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.feature_empty.entity.FeatureEmpty

class LocalComplexFeatureDataSource : ComplexFeatureDataSource{

	override suspend fun loadAllItems(): List<ComplexFeature>{
		TODO()
	}

	override suspend fun loadItemDetail(item: ComplexFeature, simpleList: FeatureEmpty): ComplexFeature{
		TODO()
	}

}
