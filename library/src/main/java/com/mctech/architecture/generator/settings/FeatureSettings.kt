package com.mctech.architecture.generator.settings

import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
data class FeatureSettings(
    val projectSettings: ProjectSettings,
    val createDependencyInjectionModules: Boolean = false,
    val fileDuplicatedStrategy: FileDuplicatedStrategy = FileDuplicatedStrategy.Ignore
)