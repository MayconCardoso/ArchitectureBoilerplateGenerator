package com.mctech.architecture.generator

import com.mctech.architecture.generator.builder.FeatureGenerator
import com.mctech.architecture.generator.builder.newFeature
import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.PresentationMode
import com.mctech.architecture.generator.settings.ProjectSettings
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

fun main() {
    val projectSettings = ProjectSettings(
        basePackageName = Package("com.mctech.architecture")
    )

    val featureSettings = FeatureSettings(
        createDependencyInjectionModules = false,
        projectSettings         = projectSettings,
        fileDuplicatedStrategy  = FileDuplicatedStrategy.Replace,
        presentationViewModel   = PresentationMode.ActivityAndFragment
    )

    // Here is an empty feature generated
    FeatureGenerator(
        settings    = featureSettings,
        featureName = "FeatureEmpty"
    ).newFeature {}
}