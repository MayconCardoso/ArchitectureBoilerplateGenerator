package com.mctech.architecture.generator.settings

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.alias.toPackageName
import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
data class FeatureSettings(
    val projectSettings: ProjectSettings,
    var fileDuplicatedStrategy: FileDuplicatedStrategy = FileDuplicatedStrategy.Ignore,
    var createDependencyInjectionModules: Boolean = false,
    var createBothRemoteAndLocalDataSources: Boolean = true
)

fun featureEntityName()         = GlobalSettings.currentFeatureName.toEntityName()
fun featureSegment()            = GlobalSettings.currentFeatureName.toSegmentalName()
fun featurePackage()            = GlobalSettings.currentFeatureName.toPackageName()
fun basePackage()               = GlobalSettings.projectSettings.basePackageName