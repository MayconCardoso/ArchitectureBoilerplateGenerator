package com.mctech.architecture.domain.complex_feature.interaction

import javax.inject.Inject
import com.mctech.architecture.domain.Result
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.service.ComplexFeatureService

class LoadItemDetailCase @Inject constructor(private val service : ComplexFeatureService){
	suspend fun execute(item: ComplexFeature) : Result<ComplexFeature>{
		return try{
			Result.Success(service.loadItemDetail(item))
		}
		catch (ex : Exception){
			ex.printStackTrace()
			Result.Failure(ex)
		}
	}
}
