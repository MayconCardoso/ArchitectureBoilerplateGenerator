package com.mctech.architecture.data.complex_feature.datasource

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature

class LocalComplexFeatureDataSource : ComplexFeatureDataSource{

	override suspend fun loadAllItems(): List<ComplexFeature>?{
		TODO()
	}

	override suspend fun loadItemDetail(item: ComplexFeature): ComplexFeature{
		TODO()
	}

}
