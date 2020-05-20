package com.mctech.architecture.domain.complex_feature.service

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.feature_empty.entity.FeatureEmpty

interface ComplexFeatureService{
	suspend fun loadAllItems(): List<ComplexFeature>
	suspend fun loadItemDetail(item: ComplexFeature, simpleList: FeatureEmpty): ComplexFeature
}
