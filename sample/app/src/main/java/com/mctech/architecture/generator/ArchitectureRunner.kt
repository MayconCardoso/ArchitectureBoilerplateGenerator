package com.mctech.architecture.generator

import com.mctech.architecture.generator.builder.FeatureGenerator
import com.mctech.architecture.generator.builder.newFeature
import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.path.ModuleFilePath
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
        projectSettings         = projectSettings,
        fileDuplicatedStrategy  = FileDuplicatedStrategy.Replace,
        presentationViewModel   = PresentationMode.ActivityAndFragment
    )

    // Here is an empty feature generated
    FeatureGenerator(
        settings    = featureSettings,
        featureName = "FeatureEmpty"
    ).newFeature {
        baseArchitecturePath = ModuleFilePath(
            moduleLocation = "sample/sample-architecture",
            packageValue = Package("com.mctech.samplesample_architecture")
        )
    }

    // Here is a complex feature with use cases and different liveData.
//    FeatureGenerator(
//        settings    = featureSettings,
//        featureName = "ComplexFeature"
//    ).newFeature {
//
//        // Override the domain layer
//        domainModulePath = ModuleFilePath(
//            moduleLocation   = "sample/domain",
//            packageValue = Package("${projectPackage}domain")
//        )
//
//        // Override the data layer
//        dataModulePath = ModuleFilePath(
//            moduleLocation   = "sample/data",
//            packageValue = Package("${projectPackage}data")
//        )
//    }
}