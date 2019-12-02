package com.mctech.architecture.generator

import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
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

    val featureSettings = FeatureSettings(
        projectSettings = projectSettings,
        createDependencyInjectionModules = false,
        fileDuplicatedStrategy = FileDuplicatedStrategy.Replace
    )

    FeatureGenerator(featureSettings, "Balance").newFeature {
        addUseCase(
            name = "GetBalanceCase",
            isDaggerInjetable = true,
            returnType = Type.ResultOf(Type.Float)
        )

        addUseCase(
            name = "SaveBalanceCase",
            isDaggerInjetable = true,
            parameters = listOf(
                Parameter(
                    name = "balance",
                    type = Type.GeneratedEntity
                )
            )
        )
        addUseCase(
            name = "TestMultipleParameters",
            isDaggerInjetable = true,
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