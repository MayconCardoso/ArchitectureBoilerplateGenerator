package io.github.mayconcardoso.boilerplate.generator

import io.github.mayconcardoso.boilerplate.generator.builder.*
import io.github.mayconcardoso.boilerplate.generator.class_contract.Package
import io.github.mayconcardoso.boilerplate.generator.class_contract.Parameter
import io.github.mayconcardoso.boilerplate.generator.class_contract.Type
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.path.projectPackage
import io.github.mayconcardoso.boilerplate.generator.settings.*
import io.github.mayconcardoso.boilerplate.generator.strategy.FileDuplicatedStrategy

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
                returnType  = Type.ResultOf(Type.ListOfGeneratedEntity),
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

        addComponentState {
            ComponentStateBuilder(
                name = "itemDetails",
                type = Type.GeneratedEntity
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
                    ),
                    Parameter(
                        name = "simpleList",
                        type = Type.CustomType(
                            packageValue = "com.mctech.architecture.domain.feature_empty.entity",
                            typeReturn = "FeatureEmpty"
                        )
                    )
                ),
                connectedState = findStateByName("itemDetails"),
                connectedUseCase = findUseCaseByName("LoadItemDetailCase")
            )
        }
    }

}