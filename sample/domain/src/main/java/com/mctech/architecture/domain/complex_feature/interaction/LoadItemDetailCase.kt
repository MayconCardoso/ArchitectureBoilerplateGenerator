package com.mctech.architecture.domain.complex_feature.interaction

import com.mctech.architecture.domain.Result
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.service.ComplexFeatureService
import com.mctech.architecture.domain.feature_empty.entity.FeatureEmpty

class LoadItemDetailCase(private val service : ComplexFeatureService){
	suspend fun execute(item: ComplexFeature, simpleList: FeatureEmpty) : Result<ComplexFeature>{
		return try{
			Result.Success(service.loadItemDetail(item, simpleList))
		}
		catch (ex : Exception){
			ex.printStackTrace()
			Result.Failure(ex)
		}
	}
}
