package com.mctech.architecture.generator

import com.mctech.architecture.generator.builder.FeatureGenerator
import com.mctech.architecture.generator.builder.UseCaseBuilder
import com.mctech.architecture.generator.builder.newFeature
import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
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

    val featureSettings = FeatureSettings(
        projectSettings = projectSettings,
        createDependencyInjectionModules = false,
        fileDuplicatedStrategy = FileDuplicatedStrategy.Replace
    )

    FeatureGenerator(
        featureSettings,
        "Balance"
    ).newFeature {

        addUseCase {
            UseCaseBuilder(
                name = "GetBalanceCase",
                isDaggerInjectable = true,
                returnType = Type.ResultOf(Type.Float)
            )
        }

        addUseCase {
            UseCaseBuilder(
                name = "GetAllBalancesCase",
                isDaggerInjectable = true,
                returnType = Type.ResultOf(
                    Type.ListOfGeneratedEntity
                )
            )
        }

        addUseCase {
            UseCaseBuilder(
                name = "SaveBalanceCase",
                isDaggerInjectable = true,
                parameters = listOf(
                    Parameter(
                        name = "balance",
                        type = Type.GeneratedEntity
                    )
                )
            )
        }

        addUseCase {
            UseCaseBuilder(
                name = "TestMultipleParameters",
                isDaggerInjectable = true,
                returnType = Type.GeneratedEntity,
                parameters = listOf(
                    Parameter(
                        name = "balance",
                        type = Type.GeneratedEntity
                    ),
                    Parameter(
                        name = "someFloat",
                        type = Type.Float
                    ),
                    Parameter(
                        name = "someInt",
                        type = Type.Int
                    ),
                    Parameter(
                        name = "someList",
                        type = Type.ListOfGeneratedEntity
                    )
                )
            )
        }
    }
}