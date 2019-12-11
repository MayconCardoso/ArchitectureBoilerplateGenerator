package com.mctech.architecture.generator

import com.mctech.architecture.generator.builder.FeatureGenerator
import com.mctech.architecture.generator.builder.newFeature
import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.path.projectPackage
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.PresentationMode
import com.mctech.architecture.generator.settings.ProjectSettings
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

fun main() {
    val projectSettings = ProjectSettings(
        baseAndroidProjectPath = "C:/Dev/Personal/ArchitectureGenerator/sample/",
        basePackageName = Package("com.mctech.architecture")
    )

    val featureSettings = FeatureSettings(
        createDependencyInjectionModules = false,
        createBothRemoteAndLocalDataSources = true,
        presentationViewModel   = PresentationMode.ActivityAndFragment,
        projectSettings         = projectSettings,
        fileDuplicatedStrategy  = FileDuplicatedStrategy.Replace
    )

    // Here is an empty feature generated
    FeatureGenerator(
        settings    = featureSettings,
        featureName = "FeatureEmpty"
    ).newFeature {
        baseArchitecturePath = ModuleFilePath(
            moduleLocation      = "sample/sample-architecture",
            gradleModuleName    = ":sample:sample-architecture",
            packageValue        = Package("com.mctech.samplesample_architecture")
        )

        domainModulePath = ModuleFilePath(
            moduleLocation = "domain",
            gradleModuleName = ":sample:domain",
            packageValue = Package("$projectPackage.domain")
        )

    }

    // Here is a complex feature with use cases and different liveData.
    FeatureGenerator(
        settings    = featureSettings,
        featureName = "ComplexFeature"
    ).newFeature {
        // Override the domain layer
        baseArchitecturePath = ModuleFilePath(
            moduleLocation      = "sample/sample-architecture",
            gradleModuleName    = ":sample:sample-architecture",
            packageValue        = Package("com.mctech.samplesample_architecture")
        )

        domainModulePath = ModuleFilePath(
            moduleLocation = "domain",
            gradleModuleName = ":sample:domain",
            packageValue = Package("$projectPackage.domain")
        )


    }
}