package com.mctech.architecture.generator.builder

import com.mctech.architecture.generator.alias.*
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.path.ModuleDefaultLayers
import com.mctech.architecture.generator.settings.FeatureSettings
import com.mctech.architecture.generator.settings.GlobalSettings
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

    // Architecture layers
    var dataModulePath          = ModuleDefaultLayers.Data.moduleFile
    var domainModulePath        = ModuleDefaultLayers.Domain.moduleFile
    var featureModulePath       = ModuleDefaultLayers.GeneratedFeature.moduleFile
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

    // Use cases
    val listOfUseCases = mutableListOf<UseCaseBuilder>()

    // Live data
    val listOfLiveData = mutableListOf<LiveDataBuilder>()

    // Component State
    val listOfComponentState = mutableListOf<ComponentStateBuilder>()

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
     * Called in order to perform the code generation and create all of the files.
     */
    fun generate() {
        // Set context
        FeatureContext.featureGenerator = this

        // Generate files
        domainEntityTemplateGenerator.generate()
        domainServiceGenerator.generate()
        dataServiceGeneratorImplTemplate.generate()
        dataDataSourceTemplateGenerator.generate()
        dataLocalDataSourceTemplateGenerator.generate()

        // Only if the feature has remote dataSource.
        if(settings.createBothRemoteAndLocalDataSources){
            dataRemoteDataSourceTemplateGenerator.generate()
            dataRetrofitAPITemplateGenerator.generate()
        }

        // Create all UseCases
        listOfUseCases.forEach {
            UseCaseTemplate(it, domainModulePath).generate()
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