package com.mctech.architecture.feature.complex_feature

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.mvvm.x.core.UserInteraction

sealed class ComplexFeatureUserInteraction: UserInteraction{
	object LoadList : ComplexFeatureUserInteraction()

	data class OpenDetails(
		val item : ComplexFeature
	) : ComplexFeatureUserInteraction()

}
