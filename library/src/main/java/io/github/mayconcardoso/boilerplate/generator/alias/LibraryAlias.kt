package io.github.mayconcardoso.boilerplate.generator.alias

import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.Template

typealias FeatureName = String

typealias FeatureEntityTemplate = KotlinTemplate
typealias FeatureServiceTemplate = KotlinTemplate
typealias FeatureServiceImplTemplate = KotlinTemplate
typealias FeatureDataSourceTemplate = KotlinTemplate
typealias FeatureLocalDataSourceTemplate = KotlinTemplate
typealias FeatureRemoteDataSourceTemplate = KotlinTemplate
typealias FeatureRetrofitAPITemplate = KotlinTemplate
typealias FeaturePresentationBuildGradle = Template
typealias FeaturePresentationActivity = KotlinTemplate
typealias FeaturePresentationFragment = KotlinTemplate
typealias FeaturePresentationViewModel = KotlinTemplate

fun FeatureName.toEntityName() = this

/**
 * It is gonna return the segmental name of the feature.
 * For example, for the feature name 'TestCamelCase' will return 'test-camel-case'
 */
fun FeatureName.toSegmentalName() =
  StringBuilder().let {
    it.append(this[0].lowercaseChar())

    for (position in 1 until length) {
      if (this[position].isUpperCase()) {
        it.append("-")
      }
      it.append(this[position].lowercaseChar())
    }

    it.toString()
  }

/**
 * It is gonna return the package name of the feature.
 * For example, for the feature name 'TestCamelCase' will return 'test_camel_case'
 */
fun FeatureName.toPackageName() =
  StringBuilder().let {
    it.append(this[0].lowercaseChar())

    for (position in 1 until length) {
      if (this[position].isUpperCase()) {
        it.append("_")
      }
      it.append(this[position].lowercaseChar())
    }

    it.toString()
  }