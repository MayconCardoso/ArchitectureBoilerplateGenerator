package com.mctech.architecture.domain.complex_feature.service

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
interface ComplexFeatureService{
	suspend fun loadAllItems(): List<ComplexFeature>?
	suspend fun loadItemDetail(item: ComplexFeature): ComplexFeature
}
