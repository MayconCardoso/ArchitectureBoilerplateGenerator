package com.mctech.architecture.generator.settings

import com.mctech.architecture.generator.alias.FeatureName
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
object GlobalSettings {
    lateinit var projectSettings: ProjectSettings
    lateinit var currentFeatureName: FeatureName
    lateinit var fileDuplicatedStrategy: FileDuplicatedStrategy
}