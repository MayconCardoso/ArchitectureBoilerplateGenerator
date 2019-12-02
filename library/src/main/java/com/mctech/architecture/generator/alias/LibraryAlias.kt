package com.mctech.architecture.generator.alias

import com.mctech.architecture.generator.templates.Template
import java.util.*

typealias FeatureName = String
typealias FeatureEntityTemplate = Template
typealias FeatureServiceTemplate = Template
typealias FeatureServiceImplTemplate = Template
typealias FeatureDataSourceTemplate = Template
typealias FeatureLocalDataSourceTemplate = Template
typealias FeatureRemoteDataSourceTemplate = Template

fun FeatureName.toEntityName() = this.toLowerCase(Locale.getDefault()).capitalize()
fun FeatureName.toSegmentalName() = this.toLowerCase(Locale.getDefault())