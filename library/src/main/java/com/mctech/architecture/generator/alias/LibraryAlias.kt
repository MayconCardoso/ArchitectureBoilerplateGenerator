package com.mctech.architecture.generator.alias

import com.mctech.architecture.generator.templates.KotlinTemplate
import com.mctech.architecture.generator.templates.Template

typealias FeatureName = String

typealias FeatureEntityTemplate             = KotlinTemplate
typealias FeatureServiceTemplate            = KotlinTemplate
typealias FeatureServiceImplTemplate        = KotlinTemplate
typealias FeatureDataSourceTemplate         = KotlinTemplate
typealias FeatureLocalDataSourceTemplate    = KotlinTemplate
typealias FeatureRemoteDataSourceTemplate   = KotlinTemplate
typealias FeatureRetrofitAPITemplate        = KotlinTemplate
typealias FeaturePresentationBuildGradle    = Template
typealias FeaturePresentationActivity       = KotlinTemplate
typealias FeaturePresentationFragment       = KotlinTemplate
typealias FeaturePresentationViewModel      = KotlinTemplate

fun FeatureName.toEntityName() = this

/**
 * It is gonna return the segmental name of the feature.
 * For example, for the feature name 'TestCamelCase' will return 'test-camel-case'
 */
fun FeatureName.toSegmentalName() = StringBuilder().let {
    it.append(this[0].toLowerCase())

    for(position in 1 until length){
        if(this[position].isUpperCase()){
            it.append("-")
        }
        it.append(this[position].toLowerCase())
    }

    it.toString()
}

/**
 * It is gonna return the package name of the feature.
 * For example, for the feature name 'TestCamelCase' will return 'test_camel_case'
 */
fun FeatureName.toPackageName() = StringBuilder().let {
    it.append(this[0].toLowerCase())

    for(position in 1 until length){
        if(this[position].isUpperCase()){
            it.append("_")
        }
        it.append(this[position].toLowerCase())
    }

    it.toString()
}