package com.mctech.architecture.domain.complex_feature.entity

import com.mctech.architecture.domain.feature_empty.entity.FeatureEmpty

data class ComplexFeature(
	val id : Long,
	val name : String,
	val anotherFeature : FeatureEmpty
)
