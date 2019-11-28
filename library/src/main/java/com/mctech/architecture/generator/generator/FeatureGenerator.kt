package com.mctech.architecture.generator.generator

import com.mctech.architecture.generator.alias.FeatureName
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.generator.entity.EmptyEntity
import com.mctech.architecture.generator.generator.entity.EntityGenerator
import com.mctech.architecture.generator.generator.usecase.UseCaseGenerator
import com.mctech.architecture.generator.path.*
import com.mctech.architecture.generator.settings.GlobalSettings
import com.mctech.architecture.generator.settings.ProjectSettings
import com.mctech.architecture.generator.templates.DataSourceInterfaceTemplate
import com.mctech.architecture.generator.templates.RepositoryInterfaceTemplate

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class FeatureGenerator(
    val projectSettings: ProjectSettings,
    val featureName: FeatureName,
    val createDependencyInjectionModules : Boolean = false
) {

    init {
        // Set global settings with the current feature.
        GlobalSettings.projectSettings = projectSettings
        GlobalSettings.currentFeatureName = featureName

        if(createDependencyInjectionModules){
            throw RuntimeException("The library does not support Dependency Injection generation yet.")
        }
    }

    private var dataModulePath      : FilePath = defaultDataPath
    private var domainModulePath    : FilePath = defaultDomainPath
    private var featureModulePath   : FilePath = generatedFeature

    private val listOfUseCases = mutableListOf<UseCaseGenerator>()
    private val listOfLiveData = mutableListOf<String>()
    private var entityGenerator : EntityGenerator = EmptyEntity(domainModulePath)

    fun generateUseCase(
        name : String,
        returnType : Type = Type.Unit,
        parameters : List<Parameter> = listOf()
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
        name : String,
        dataType : Type = Type.Unit
    ) {

    }
    fun generateComponentState(
        name : String,
        dataType : Type = Type.Unit
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