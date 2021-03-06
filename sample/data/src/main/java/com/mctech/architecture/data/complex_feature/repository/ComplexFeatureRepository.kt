package com.mctech.architecture.data.complex_feature.repository

import com.mctech.architecture.data.complex_feature.datasource.LocalComplexFeatureDataSource
import com.mctech.architecture.data.complex_feature.datasource.RemoteComplexFeatureDataSource
import com.mctech.architecture.domain.complex_feature.entity.ComplexFeature
import com.mctech.architecture.domain.complex_feature.service.ComplexFeatureService
import com.mctech.architecture.domain.feature_empty.entity.FeatureEmpty

class ComplexFeatureRepository(
	private val localDataSource: LocalComplexFeatureDataSource,
	private val remoteDataSource: RemoteComplexFeatureDataSource
) : ComplexFeatureService{

	override suspend fun loadAllItems(): List<ComplexFeature>{
		return try{
			remoteDataSource.loadAllItems().apply {
				TODO("Here you must call the local dataSource to save it on cache")
			}
		} catch (ex : Exception){
			localDataSource.loadAllItems()
		}
	}

	override suspend fun loadItemDetail(item: ComplexFeature, simpleList: FeatureEmpty): ComplexFeature{
		return try{
			remoteDataSource.loadItemDetail(item, simpleList).apply {
				TODO("Here you must call the local dataSource to save it on cache")
			}
		} catch (ex : Exception){
			localDataSource.loadItemDetail(item, simpleList)
		}
	}

}
