package com.mctech.architecture.generator.generator

import com.mctech.architecture.generator.alias.*
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.usecase.UseCase
import com.mctech.architecture.generator.path.ModuleDefaultLayers
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.GlobalSettings
import com.mctech.architecture.generator.templates.data.DataSourceInterfaceTemplate
import com.mctech.architecture.generator.templates.data.LocalDataSourceTemplate
import com.mctech.architecture.generator.templates.data.RemoteDataSourceTemplate
import com.mctech.architecture.generator.templates.data.RepositoryTemplate
import com.mctech.architecture.generator.templates.domain.entity.EmptyEntityTemplate
import com.mctech.architecture.generator.templates.domain.interaction.UseCaseTemplate
import com.mctech.architecture.generator.templates.domain.service.ServiceInterfaceTemplate

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class FeatureGenerator(val settings: FeatureSettings, featureName: FeatureName) {
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
    var entityTemplateGenerator             : FeatureEntityTemplate = EmptyEntityTemplate(domainModulePath)
    var serviceGenerator                    : FeatureServiceTemplate = ServiceInterfaceTemplate(domainModulePath)
    var serviceGeneratorImplTemplate        : FeatureServiceImplTemplate = RepositoryTemplate(dataModulePath)
    var dataSourceTemplateGenerator         : FeatureDataSourceTemplate = DataSourceInterfaceTemplate(dataModulePath)
    var localDataSourceTemplateGenerator    : FeatureLocalDataSourceTemplate = LocalDataSourceTemplate(dataModulePath)
    var remoteDataSourceTemplateGenerator   : FeatureRemoteDataSourceTemplate = RemoteDataSourceTemplate(dataModulePath)

    // Use cases
    private val listOfUseCases = mutableListOf<UseCase>()

    // Live data
    private val listOfLiveData = mutableListOf<String>()

    fun addUseCase(
        name: String,
        returnType: Type = Type.Unit,
        parameters: List<Parameter> = listOf(),
        isDaggerInjetable : Boolean = false
    ) {
        listOfUseCases.add(
            UseCase(
                modulePath = domainModulePath,
                name = name,
                returnType = returnType,
                parameters = parameters,
                isDaggerInjectable = isDaggerInjetable
            )
        )
    }

    fun addLiveData(
        name: String,
        dataType: Type = Type.Unit
    ) {

    }

    fun addComponentState(
        name: String,
        dataType: Type = Type.Unit
    ) {

    }

    fun generate() {
        // Set context
        FeatureContext.featureGenerator = this

        // Generate files
        entityTemplateGenerator.generate()
        serviceGenerator.generate()
        serviceGeneratorImplTemplate.generate()
        dataSourceTemplateGenerator.generate()
        localDataSourceTemplateGenerator.generate()
        if(settings.createBothRemoteAndLocalDataSources){
            remoteDataSourceTemplateGenerator.generate()
        }

        // Create all UseCases
        listOfUseCases.forEach {
            UseCaseTemplate(it, domainModulePath).generate()
        }
    }
}

inline fun FeatureGenerator.newFeature(block: FeatureGenerator.() -> Unit): FeatureGenerator {
    block()
    generate()
    return this
}