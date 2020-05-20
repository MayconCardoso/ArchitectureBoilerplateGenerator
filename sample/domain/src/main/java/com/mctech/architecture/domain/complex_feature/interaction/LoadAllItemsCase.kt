package com.mctech.architecture.domain.complex_feature.interaction

import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.service.ComplexFeatureService

class LoadAllItemsCase(private val service : ComplexFeatureService){
	suspend fun execute() : List<ComplexFeature>?{
		return try{
			service.loadAllItems()
		}
		catch (ex : Exception){
			ex.printStackTrace()
			TODO("You must handle the error here.")
		}
	}
}
