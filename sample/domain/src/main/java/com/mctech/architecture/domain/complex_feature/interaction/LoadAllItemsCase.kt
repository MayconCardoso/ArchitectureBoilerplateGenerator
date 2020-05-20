package com.mctech.architecture.domain.complex_feature.interaction

import com.mctech.architecture.domain.Result
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.service.ComplexFeatureService

class LoadAllItemsCase(private val service : ComplexFeatureService){
	suspend fun execute() : Result<List<ComplexFeature>>{
		return try{
			Result.Success(service.loadAllItems())
		}
		catch (ex : Exception){
			ex.printStackTrace()
			Result.Failure(ex)
		}
	}
}
