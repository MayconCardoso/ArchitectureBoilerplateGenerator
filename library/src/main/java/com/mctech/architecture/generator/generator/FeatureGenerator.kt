package com.mctech.architecture.generator.generator

import com.mctech.architecture.generator.alias.FeatureName
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.generator.entity.EmptyEntity
import com.mctech.architecture.generator.generator.entity.EntityGenerator
import com.mctech.architecture.generator.generator.usecase.UseCaseGenerator
import com.mctech.architecture.generator.path.ModuleDefaultLayers
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.GlobalSettings
import com.mctech.architecture.generator.settings.ProjectSettings
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy
import com.mctech.architecture.generator.templates.DataSourceInterfaceTemplate
import com.mctech.architecture.generator.templates.RepositoryInterfaceTemplate

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class FeatureGenerator(settings: FeatureSettings, featureName: FeatureName){
    init {
        // Set global settings with the current feature.
        GlobalSettings.projectSettings = settings.projectSettings
        GlobalSettings.currentFeatureName = featureName
        GlobalSettings.fileDuplicatedStrategy = settings.fileDuplicatedStrategy

        if (settings.createDependencyInjectionModules) {
            throw RuntimeException("The library does not support Dependency Injection generation yet.")
        }
    }

    private var dataModulePath = ModuleDefaultLayers.Data.moduleFile
    private var domainModulePath = ModuleDefaultLayers.Domain.moduleFile
    private var featureModulePath = ModuleDefaultLayers.GeneratedFeature.moduleFile

    private var entityGenerator: EntityGenerator = EmptyEntity(domainModulePath)
    private val listOfUseCases = mutableListOf<UseCaseGenerator>()
    private val listOfLiveData = mutableListOf<String>()

    fun generateUseCase(
        name: String,
        returnType: Type = Type.Unit,
        parameters: List<Parameter> = listOf()
    ) {
        listOfUseCases.add(
            UseCaseGenerator(
                domainModulePath,
                name,
                returnType,
                parameters
            )
        )
    }

    fun generateLiveData(
        name: String,
        dataType: Type = Type.Unit
    ) {

    }

    fun generateComponentState(
        name: String,
        dataType: Type = Type.Unit
    ) {

    }

    fun generate() {
        // Create the entity
        entityGenerator.generate()

        // Generate DataSource Interface
        TemplateGenerator(dataModulePath, DataSourceInterfaceTemplate()).generate()

        // Generate Repository Interface
        TemplateGenerator(dataModulePath, RepositoryInterfaceTemplate()).generate()

        // Create all UseCases
        listOfUseCases.forEach {
            it.generate()
        }
    }
}

inline fun FeatureGenerator.newFeature(block: FeatureGenerator.() -> Unit): FeatureGenerator {
    block()
    generate()
    return this
}