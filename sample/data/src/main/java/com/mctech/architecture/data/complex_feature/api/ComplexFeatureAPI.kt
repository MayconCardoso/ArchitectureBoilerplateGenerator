package com.mctech.architecture.data.complex_feature.api

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature

interface ComplexFeatureAPI{
	fun loadAllItems(): List<ComplexFeature>?
	fun loadItemDetail(item: ComplexFeature): ComplexFeature
}
