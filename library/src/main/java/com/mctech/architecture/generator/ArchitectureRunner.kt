package com.mctech.architecture.generator

import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.generator.FeatureGenerator
import com.mctech.architecture.generator.generator.newFeature
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.ProjectSettings
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
fun main() {

    val projectSettings = ProjectSettings(
        basePackageName = Package("com.mctech.architecture"),
        isTheProjectModularized = true
    )

    val featureSettins = FeatureSettings(
        projectSettings = projectSettings,
        createDependencyInjectionModules = false,
        fileDuplicatedStrategy = FileDuplicatedStrategy.Replace
    )

    FeatureGenerator(featureSettins, "Investment").newFeature {}
}