package com.mctech.architecture.generator

import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.generator.FeatureGenerator
import com.mctech.architecture.generator.generator.newFeature
import com.mctech.architecture.generator.settings.ProjectSettings

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
fun main() {
    val projectSettings = ProjectSettings(
        basePackageName = "com.mctech.sample",
        isTheProjectModularized = true
    )

    FeatureGenerator(
        projectSettings = projectSettings,
        featureName = "Investment",
        createDependencyInjectionModules = false
    ).newFeature {

        generateUseCase(
            name = "SaveInvestment"
        )

        generateUseCase(
            name = "GetBalance",
            returnType = Type.Float
        )

        generateUseCase(
            name = "GetAllInvestments",
            returnType = Type.GeneratedEntity,
            parameters = listOf(
                Parameter(
                    name = "token",
                    type = Type.String
                ),
                Parameter(
                    name = "userId",
                    type = Type.Int
                )
            )
        )

        generateLiveData(
            name = "testeLiveData",
            dataType = Type.Int
        )

        generateLiveData(
            name = "allInvestments",
            dataType = Type.ListOfGeneratedEntity
        )
    }
}