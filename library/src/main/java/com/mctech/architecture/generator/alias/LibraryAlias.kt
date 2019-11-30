package com.mctech.architecture.generator.alias

import com.mctech.architecture.generator.templates.Template
import java.util.*

typealias FeatureName = String
typealias FeatureEntity = Template
typealias FeatureService = Template
typealias FeatureServiceImpl = Template
typealias FeatureDataSource = Template
typealias FeatureDataSourceImpl = Template

fun FeatureName.toEntityName() = this.toLowerCase(Locale.getDefault()).capitalize()
fun FeatureName.toSegmentalName() = this.toLowerCase(Locale.getDefault())