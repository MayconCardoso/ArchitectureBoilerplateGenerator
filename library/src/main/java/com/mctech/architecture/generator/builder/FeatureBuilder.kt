package com.mctech.architecture.generator.builder

import com.mctech.architecture.generator.alias.*
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.path.ModuleDefaultLayers
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.GlobalSettings
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy
import com.mctech.architecture.generator.templates.data.api.RetrofitAPITemplate
import com.mctech.architecture.generator.templates.data.datasource.DataSourceInterfaceTemplate
import com.mctech.architecture.generator.templates.data.datasource.LocalDataSourceTemplate
import com.mctech.architecture.generator.templates.data.datasource.RemoteDataSourceTemplate
import com.mctech.architecture.generator.templates.data.repository.RepositoryTemplate
import com.mctech.architecture.generator.templates.domain.entity.EmptyEntityTemplate
import com.mctech.architecture.generator.templates.domain.interaction.UseCaseTemplate
import com.mctech.architecture.generator.templates.domain.service.ServiceInterfaceTemplate
import com.mctech.architecture.generator.templates.presentation.kotlin.ActvityTemplate
import com.mctech.architecture.generator.templates.presentation.kotlin.FragmentTemplate
import com.mctech.architecture.generator.templates.presentation.kotlin.ViewInteractionTemplate
import com.mctech.architecture.generator.templates.presentation.kotlin.ViewModelTemplate
import com.mctech.architecture.generator.templates.presentation.manifest.AndroidManifestTemplate
import com.mctech.architecture.generator.templates.presentation.module.AddFeatureOnSettingsFileTemplate
import com.mctech.architecture.generator.templates.presentation.module.GradleModuleTemplate
import com.mctech.architecture.generator.templates.presentation.resource.ActivityLayoutTemplate
import com.mctech.architecture.generator.templates.presentation.resource.FragmentLayoutTemplate
import com.mctech.architecture.generator.templates.presentation.resource.StringTemplate

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class FeatureGenerator(
    val settings: FeatureSettings,
    val featureName: FeatureName
) {
    init {
        // Set global settings with the current feature.
        GlobalSettings.projectSettings = settings.projectSettings
        GlobalSettings.fileDuplicatedStrategy = settings.fileDuplicatedStrategy

        if (settings.createDependencyInjectionModules) {
            throw RuntimeException("The library does not support Dependency Injection generation yet.")
        }

        // Set context
        FeatureContext.featureGenerator = this
    }

    // Architecture layers
    var dataModulePath  = ModuleDefaultLayers.Data.moduleFile
        set(value) { setUpDataTemplates(value) }

    var domainModulePath = ModuleDefaultLayers.Domain.moduleFile
        set(value) { setUpDomainTemplates(value) }

    var featureModulePath = ModuleDefaultLayers.GeneratedFeature.moduleFile
        set(value) { setUpFeatureTemplates(value) }

    var baseArchitecturePath    = ModuleDefaultLayers.BaseArchitecture.moduleFile

    // Templates domain Generators
    var domainEntityTemplateGenerator           : FeatureEntityTemplate             = EmptyEntityTemplate(domainModulePath)
    var domainServiceGenerator                  : FeatureServiceTemplate            = ServiceInterfaceTemplate(domainModulePath)

    // Templates data Generators
    var dataServiceGeneratorImplTemplate        : FeatureServiceImplTemplate        = RepositoryTemplate(dataModulePath)
    var dataDataSourceTemplateGenerator         : FeatureDataSourceTemplate         = DataSourceInterfaceTemplate(dataModulePath)
    var dataLocalDataSourceTemplateGenerator    : FeatureLocalDataSourceTemplate    = LocalDataSourceTemplate(dataModulePath)
    var dataRemoteDataSourceTemplateGenerator   : FeatureRemoteDataSourceTemplate   = RemoteDataSourceTemplate(dataModulePath)
    var dataRetrofitAPITemplateGenerator        : FeatureRetrofitAPITemplate        = RetrofitAPITemplate(dataModulePath)

    // Template presentation Generators
    var presentationBuildGradle                 : FeaturePresentationBuildGradle    = GradleModuleTemplate(featureModulePath)
    var presentationActivity                    : FeaturePresentationActivity       = ActvityTemplate(featureModulePath)
    var presentationFragment                    : FeaturePresentationFragment       = FragmentTemplate(featureModulePath)
    var presentationViewModel                   : FeaturePresentationViewModel      = ViewModelTemplate(featureModulePath)

    // Entity fields
    val listOfFieldsOnEntity = mutableListOf<Parameter>()

    // Use cases
    val listOfUseCases = mutableListOf<UseCaseBuilder>()

    // Live data
    val listOfLiveData = mutableListOf<LiveDataBuilder>()

    // Component State
    val listOfComponentState = mutableListOf<ComponentStateBuilder>()

    // Interactions
    val listOfUserInteraction = mutableListOf<UserInteractionBuilder>()

    // View commands.
    val listOfViewCommand = mutableListOf<ViewCommandBuilder>()

    /**
     * Add fields on entity
     */
    fun addEntityField(field : Parameter) {
        listOfFieldsOnEntity.add(field)
    }

    /**
     * Every UseCase added on the feature will create the following code:
     * - Use case file
     * - Method signature on services, repositories, data sources and API files.
     */
    fun addUseCase(block : () -> UseCaseBuilder) {
        listOfUseCases.add(block.invoke().apply {
            modulePath = domainModulePath
        })
    }

    /**
     * Every LiveDataBuilder added on the feature will create the following code:
     * - The LiveData private and public inside the ViewModel
     * - Method signature on activity and service calling it.
     */
    fun addLiveData(block: () -> LiveDataBuilder) {
        listOfLiveData.add(block.invoke())
    }

    /**
     * Every ComponentStateBuilder added on the feature will create the following code:
     * - The LiveData private and public inside the ViewModel
     * - Method signature on activity and service calling it.
     */
    fun addComponentState(block: () -> ComponentStateBuilder) {
        listOfComponentState.add(block.invoke())
    }

    /**
     * Add user interactions.
     */
    fun addUserInteraction(block: () -> UserInteractionBuilder) {
        listOfUserInteraction.add(block.invoke())
    }

    /**
     * Add user interactions.
     */
    fun addViewCommand(block: () -> ViewCommandBuilder) {
        listOfViewCommand.add(block.invoke())
    }

    /**
     * Return the use case by its name.
     */
    fun findUseCaseByName(name : String) : UseCaseBuilder{
        return listOfUseCases.first {
            it.name == name
        }
    }

    /**
     * Called in order to perform the code generation and create all of the files.
     */
    fun generate() {
        // Set context
        FeatureContext.featureGenerator = this

        // Need to ignore feature
        if(
            settings.featureDuplicatedStrategy is FileDuplicatedStrategy.Ignore
            && AddFeatureOnSettingsFileTemplate().containsFeature()
        ){
            println("Ignoring feature: ${FeatureContext.featureGenerator.featureName}")
            return
        }


        // Generate DOMAIN files
        domainEntityTemplateGenerator.generate()
        domainServiceGenerator.generate()
        listOfUseCases.forEach {
            UseCaseTemplate(it, domainModulePath).generate()
        }


        // Generate DATA files
        dataServiceGeneratorImplTemplate.generate()
        dataDataSourceTemplateGenerator.generate()
        dataLocalDataSourceTemplateGenerator.generate()
        if(settings.createBothRemoteAndLocalDataSources){
            dataRemoteDataSourceTemplateGenerator.generate()
            dataRetrofitAPITemplateGenerator.generate()
        }



        // Create final templates of the feature module.
        AddFeatureOnSettingsFileTemplate().generate()
        AndroidManifestTemplate(featureModulePath).generate()
        StringTemplate(featureModulePath).generate()

        if(settings.presentationViewModel.hasActivity){
            ActivityLayoutTemplate(featureModulePath).generate()
            presentationActivity.generate()
        }

        if(settings.presentationViewModel.hasFragment){
            FragmentLayoutTemplate(featureModulePath).generate()
            presentationFragment.generate()
        }

        presentationBuildGradle.generate()
        presentationViewModel.generate()
        if(listOfUserInteraction.isNotEmpty()){
            ViewInteractionTemplate(featureModulePath).generate()
        }
    }


    private fun setUpDataTemplates(dataModulePath: ModuleFilePath) {
        dataServiceGeneratorImplTemplate        = RepositoryTemplate(dataModulePath)
        dataDataSourceTemplateGenerator         = DataSourceInterfaceTemplate(dataModulePath)
        dataLocalDataSourceTemplateGenerator    = LocalDataSourceTemplate(dataModulePath)
        dataRemoteDataSourceTemplateGenerator   = RemoteDataSourceTemplate(dataModulePath)
        dataRetrofitAPITemplateGenerator        = RetrofitAPITemplate(dataModulePath)
    }

    private fun setUpDomainTemplates(domainModulePath: ModuleFilePath) {
        domainEntityTemplateGenerator   = EmptyEntityTemplate(domainModulePath)
        domainServiceGenerator          = ServiceInterfaceTemplate(domainModulePath)
    }

    private fun setUpFeatureTemplates(featureModulePath: ModuleFilePath) {
        presentationBuildGradle     = GradleModuleTemplate(featureModulePath)
        presentationActivity        = ActvityTemplate(featureModulePath)
        presentationFragment        = FragmentTemplate(featureModulePath)
        presentationViewModel       = ViewModelTemplate(featureModulePath)
    }
}

/**
 * Function to facilitate when creating features.
 */
inline fun FeatureGenerator.newFeature(block: FeatureGenerator.() -> Unit): FeatureGenerator {
    block()
    generate()
    return this
}

/**
 * Iterate on each use case to facilitate routines of generating code with it.
 */
inline fun foreachUseCase(block: (useCase : UseCaseBuilder) -> Unit) {
    FeatureContext.featureGenerator.listOfUseCases.forEach {
        block(it)
    }
}

/**
 * Iterate on each liveData to facilitate routines of generating code with it.
 */
inline fun foreachLiveData(block: (useCase : LiveDataBuilder) -> Unit) {
    FeatureContext.featureGenerator.listOfLiveData.forEach {
        block(it)
    }
}

/**
 * Iterate on each ComponentState to facilitate routines of generating code with it.
 */
inline fun foreachComponentState(block: (useCase : ComponentStateBuilder) -> Unit) {
    FeatureContext.featureGenerator.listOfComponentState.forEach {
        block(it)
    }
}