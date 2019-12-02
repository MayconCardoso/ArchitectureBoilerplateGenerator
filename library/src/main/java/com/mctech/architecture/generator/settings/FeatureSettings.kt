package com.mctech.architecture.generator.settings

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
data class FeatureSettings(
    val projectSettings: ProjectSettings,
    val fileDuplicatedStrategy: FileDuplicatedStrategy = FileDuplicatedStrategy.Ignore,
    val createDependencyInjectionModules: Boolean = false,
    val createBothRemoteAndLocalDataSources: Boolean = true
)

fun featureEntityName()         = GlobalSettings.currentFeatureName.toEntityName()
fun featureSegment()            = GlobalSettings.currentFeatureName.toSegmentalName()
fun basePackage()               = GlobalSettings.projectSettings.basePackageName