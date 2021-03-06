package com.mctech.architecture.data.feature_empty.repository

import com.mctech.architecture.data.feature_empty.datasource.LocalFeatureEmptyDataSource
import com.mctech.architecture.data.feature_empty.datasource.RemoteFeatureEmptyDataSource
import com.mctech.architecture.domain.feature_empty.service.FeatureEmptyService

class FeatureEmptyRepository(
	private val localDataSource: LocalFeatureEmptyDataSource,
	private val remoteDataSource: RemoteFeatureEmptyDataSource
) : FeatureEmptyService{

}
