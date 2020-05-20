package com.mctech.architecture.generator

import com.mctech.architecture.generator.builder.*
import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.path.projectPackage
import com.mctech.architecture.generator.settings.*
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

fun main() {
    val projectSettings = ProjectSettings(
        baseAndroidProjectPath = "sample/",
        basePackageName = Package("com.mctech.architecture")
    )

    val featureSettings = FeatureSettings(
        createDependencyInjectionModules = false,
        createBothRemoteAndLocalDataSources = true,
        presentationViewModel       = PresentationMode.ActivityAndFragment,
        projectSettings             = projectSettings,
        fileDuplicatedStrategy      = FileDuplicatedStrategy.Replace,
        featureDuplicatedStrategy   = FileDuplicatedStrategy.Ignore
    )

    // Here is an empty feature generated
    FeatureGenerator.newFeature(
        settings    = featureSettings,
        featureName = "FeatureEmpty"
    ) {
         dataModulePath = ModuleFilePath(
            moduleLocation = "data",
            gradleModuleName = ":sample:data",
            packageValue = Package("$projectPackage.data")
        )

        domainModulePath = ModuleFilePath(
            moduleLocation = "domain",
            gradleModuleName = ":sample:domain",
            packageValue = Package("$projectPackage.domain")
        )

        featureModulePath = ModuleFilePath(
            moduleLocation = "features/feature-${featureSegment()}",
            gradleModuleName = ":sample:features:feature-${featureSegment()}",
            packageValue = Package("$projectPackage.feature.${featurePackage()}")
        )
    }

    // Here is a complex feature with use cases and different liveData.
    FeatureGenerator.newFeature(
        settings    = featureSettings,
        featureName = "ComplexFeature"
    ) {
        dataModulePath = ModuleFilePath(
            moduleLocation = "data",
            gradleModuleName = ":sample:data",
            packageValue = Package("$projectPackage.data")
        )

        domainModulePath = ModuleFilePath(
            moduleLocation = "domain",
            gradleModuleName = ":sample:domain",
            packageValue = Package("$projectPackage.domain")
        )

        featureModulePath = ModuleFilePath(
            moduleLocation = "features/feature-${featureSegment()}",
            gradleModuleName = ":sample:features:feature-${featureSegment()}",
            packageValue = Package("$projectPackage.feature.${featurePackage()}")
        )

        // Create an use case that will call the repository and delegate it to the data sources and so on.
        addUseCase {
            UseCaseBuilder(
                name        = "LoadAllItemsCase",
                returnType  = Type.ListOfGeneratedEntity
            )
        }

        addUseCase {
            UseCaseBuilder(
                name        = "LoadItemDetailCase",
                returnType  = Type.ResultOf(Type.GeneratedEntity),
                parameters  = listOf(
                    Parameter(
                        name = "item",
                        type = Type.GeneratedEntity
                    )
                )
            )
        }

        addLiveData {
            LiveDataBuilder(
                name = "items",
                type = Type.ListOfGeneratedEntity
            )
        }

        addLiveData {
            LiveDataBuilder(
                name = "userName",
                type = Type.String
            )
        }
    }

    // Here is a complete feature
    FeatureGenerator.newFeature(
        settings    = featureSettings,
        featureName = "CompleteFeature"
    ) {
        dataModulePath = ModuleFilePath(
            moduleLocation = "data",
            gradleModuleName = ":sample:data",
            packageValue = Package("$projectPackage.data")
        )

        domainModulePath = ModuleFilePath(
            moduleLocation = "domain",
            gradleModuleName = ":sample:domain",
            packageValue = Package("$projectPackage.domain")
        )

        featureModulePath = ModuleFilePath(
            moduleLocation = "features/feature-${featureSegment()}",
            gradleModuleName = ":sample:features:feature-${featureSegment()}",
            packageValue = Package("$projectPackage.feature.${featurePackage()}")
        )

        // Add fields on entity
        addEntityField(Parameter(
            name = "id", type = Type.Long
        ))

        addEntityField(Parameter(
            name = "name", type = Type.String
        ))

        addEntityField(Parameter(
            name = "anotherFeature", type = Type.CustomType(
                packageValue = "com.mctech.architecture.domain.feature_empty.entity",
                typeReturn = "FeatureEmpty"
            )
        ))


        // Create an use case that will call the repository and delegate it to the data sources and so on.
        addUseCase {
            UseCaseBuilder(
                name        = "LoadAllItemsCase",
                returnType  = Type.ListOfGeneratedEntity,
                isDaggerInjectable = false
            )
        }

        addUseCase {
            UseCaseBuilder(
                name        = "LoadItemDetailCase",
                returnType  = Type.ResultOf(Type.GeneratedEntity),
                parameters  = listOf(
                    Parameter(
                        name = "item",
                        type = Type.GeneratedEntity
                    ),
                    Parameter(
                        name = "simpleList",
                        type = Type.CustomType(
                            packageValue = "com.mctech.architecture.domain.feature_empty.entity",
                            typeReturn = "FeatureEmpty"
                        )
                    )
                ),
                isDaggerInjectable = false
            )
        }

        addLiveData {
            LiveDataBuilder(
                name = "items",
                type = Type.ListOfGeneratedEntity
            )
        }

        addLiveData {
            LiveDataBuilder(
                name = "userName",
                type = Type.String
            )
        }

        addComponentState {
            ComponentStateBuilder(
                name = "listEntities",
                type = Type.ListOfGeneratedEntity
            )
        }

        addUserInteraction {
            UserInteractionBuilder(
                name = "LoadList",
                connectedState = findStateByName("listEntities"),
                connectedUseCase = findUseCaseByName("LoadAllItemsCase")
            )
        }

        addUserInteraction {
            UserInteractionBuilder(
                name = "OpenDetails",
                parameters = listOf(
                    Parameter(
                        name = "item",
                        type = Type.GeneratedEntity
                    )
                ),
                connectedUseCase = findUseCaseByName("LoadItemDetailCase")
            )
        }
    }

}