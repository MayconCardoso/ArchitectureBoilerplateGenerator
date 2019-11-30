package com.mctech.architecture.generator.generator

import com.mctech.architecture.generator.alias.*
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.usecase.UseCaseGenerator
import com.mctech.architecture.generator.path.ModuleDefaultLayers
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.GlobalSettings
import com.mctech.architecture.generator.templates.data.DataSourceInterfaceTemplate
import com.mctech.architecture.generator.templates.data.LocalDataSourceTemplate
import com.mctech.architecture.generator.templates.data.RemoteDataSourceTemplate
import com.mctech.architecture.generator.templates.data.RepositoryTemplate
import com.mctech.architecture.generator.templates.domain.entity.EmptyEntityTemplate
import com.mctech.architecture.generator.templates.domain.service.ServiceInterfaceTemplate

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class FeatureGenerator(settings: FeatureSettings, featureName: FeatureName) {
    init {
        // Set global settings with the current feature.
        GlobalSettings.projectSettings = settings.projectSettings
        GlobalSettings.currentFeatureName = featureName
        GlobalSettings.fileDuplicatedStrategy = settings.fileDuplicatedStrategy

        if (settings.createDependencyInjectionModules) {
            throw RuntimeException("The library does not support Dependency Injection generation yet.")
        }
    }

    // Architecture
    var dataModulePath = ModuleDefaultLayers.Data.moduleFile
    var domainModulePath = ModuleDefaultLayers.Domain.moduleFile
    var featureModulePath = ModuleDefaultLayers.GeneratedFeature.moduleFile

    // Generators
    var entityGenerator             : FeatureEntity = EmptyEntityTemplate(domainModulePath)
    var serviceGenerator            : FeatureService = ServiceInterfaceTemplate(domainModulePath)
    var serviceGeneratorImpl        : FeatureServiceImpl = RepositoryTemplate(dataModulePath)
    var dataSourceGenerator         : FeatureDataSource = DataSourceInterfaceTemplate(dataModulePath)
    var localDataSourceGenerator    : FeatureDataSource = LocalDataSourceTemplate(dataModulePath)
    var remoteDataSourceGenerator   : FeatureDataSource = RemoteDataSourceTemplate(dataModulePath)

    // Use cases
    private val listOfUseCases = mutableListOf<UseCaseGenerator>()

    // Live data
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
        // Set context
        FeatureContext.featureGenerator = this

        // Generate files
        entityGenerator.generate()
        serviceGenerator.generate()
        serviceGeneratorImpl.generate()
        dataSourceGenerator.generate()
        localDataSourceGenerator.generate()
        remoteDataSourceGenerator.generate()

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