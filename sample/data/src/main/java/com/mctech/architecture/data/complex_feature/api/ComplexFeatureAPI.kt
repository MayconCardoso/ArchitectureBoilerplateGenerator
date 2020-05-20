package com.mctech.architecture.data.complex_feature.api

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.feature_empty.entity.FeatureEmpty

interface ComplexFeatureAPI{
	fun loadAllItems(): List<ComplexFeature>?
	fun loadItemDetail(item: ComplexFeature, simpleList: FeatureEmpty): ComplexFeature
}
