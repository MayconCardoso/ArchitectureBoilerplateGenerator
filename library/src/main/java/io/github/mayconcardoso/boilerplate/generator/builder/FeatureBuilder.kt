package io.github.mayconcardoso.boilerplate.generator.builder

import io.github.mayconcardoso.boilerplate.generator.alias.FeatureName
import io.github.mayconcardoso.boilerplate.generator.class_contract.Parameter
import io.github.mayconcardoso.boilerplate.generator.class_contract.customTypeImport
import io.github.mayconcardoso.boilerplate.generator.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.path.ModuleDefaultLayers
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.FeatureSettings
import io.github.mayconcardoso.boilerplate.generator.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.strategy.FileDuplicatedStrategy
import io.github.mayconcardoso.boilerplate.generator.templates.data.api.RetrofitAPITemplate
import io.github.mayconcardoso.boilerplate.generator.templates.data.datasource.DataSourceInterfaceTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.data.datasource.LocalDataSourceTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.data.datasource.RemoteDataSourceTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.data.repository.RepositoryTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.domain.entity.EmptyEntityTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.domain.interaction.UseCaseTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.domain.service.ServiceInterfaceTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin.ActvityTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin.FragmentTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin.ViewInteractionTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin.ViewModelTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.manifest.AndroidManifestTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.module.AddFeatureOnSettingsFileTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.module.GradleModuleTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.resource.ActivityLayoutTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.resource.FragmentLayoutTemplate
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.resource.StringTemplate
import java.io.PrintWriter

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
  var dataModulePath = ModuleDefaultLayers.Data.moduleFile
    set(value) {
      field = value
      setUpDataTemplates(value)
    }

  var domainModulePath = ModuleDefaultLayers.Domain.moduleFile
    set(value) {
      field = value
      setUpDomainTemplates(value)
    }

  var featureModulePath = ModuleDefaultLayers.GeneratedFeature.moduleFile
    set(value) {
      field = value
      setUpFeatureTemplates(value)
    }

  var baseArchitecturePath = ModuleDefaultLayers.BaseArchitecture.moduleFile

  // Templates domain Generators
  var domainEntityTemplateGenerator: io.github.mayconcardoso.boilerplate.generator.alias.FeatureEntityTemplate =
    EmptyEntityTemplate(domainModulePath)
  var domainServiceGenerator: io.github.mayconcardoso.boilerplate.generator.alias.FeatureServiceTemplate =
    ServiceInterfaceTemplate(domainModulePath)

  // Templates data Generators
  var dataServiceGeneratorImplTemplate: io.github.mayconcardoso.boilerplate.generator.alias.FeatureServiceImplTemplate =
    RepositoryTemplate(dataModulePath)
  var dataDataSourceTemplateGenerator: io.github.mayconcardoso.boilerplate.generator.alias.FeatureDataSourceTemplate =
    DataSourceInterfaceTemplate(dataModulePath)
  var dataLocalDataSourceTemplateGenerator: io.github.mayconcardoso.boilerplate.generator.alias.FeatureLocalDataSourceTemplate =
    LocalDataSourceTemplate(dataModulePath)
  var dataRemoteDataSourceTemplateGenerator: io.github.mayconcardoso.boilerplate.generator.alias.FeatureRemoteDataSourceTemplate =
    RemoteDataSourceTemplate(dataModulePath)
  var dataRetrofitAPITemplateGenerator: io.github.mayconcardoso.boilerplate.generator.alias.FeatureRetrofitAPITemplate =
    RetrofitAPITemplate(dataModulePath)

  // Template presentation Generators
  var presentationBuildGradle: io.github.mayconcardoso.boilerplate.generator.alias.FeaturePresentationBuildGradle =
    GradleModuleTemplate(featureModulePath)
  var presentationActivity: io.github.mayconcardoso.boilerplate.generator.alias.FeaturePresentationActivity =
    ActvityTemplate(featureModulePath)
  var presentationFragment: io.github.mayconcardoso.boilerplate.generator.alias.FeaturePresentationFragment =
    FragmentTemplate(featureModulePath)
  var presentationViewModel: io.github.mayconcardoso.boilerplate.generator.alias.FeaturePresentationViewModel =
    ViewModelTemplate(featureModulePath)

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
  fun addEntityField(field: Parameter) {
    listOfFieldsOnEntity.add(field)
  }

  /**
   * Every UseCase added on the feature will create the following code:
   * - Use case file
   * - Method signature on services, repositories, data sources and API files.
   */
  fun addUseCase(block: () -> UseCaseBuilder) {
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
  fun findUseCaseByName(name: String): UseCaseBuilder {
    return listOfUseCases.first {
      it.name == name
    }
  }

  /**
   * Return the component state by its name.
   */
  fun findStateByName(name: String): ComponentStateBuilder {
    return listOfComponentState.first {
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
    if (
      settings.featureDuplicatedStrategy is FileDuplicatedStrategy.Ignore
      && AddFeatureOnSettingsFileTemplate().containsFeature()
    ) {
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
    if (settings.createBothRemoteAndLocalDataSources) {
      dataRemoteDataSourceTemplateGenerator.generate()
      dataRetrofitAPITemplateGenerator.generate()
    }


    // Create final templates of the feature module.
    AddFeatureOnSettingsFileTemplate().generate()
    AndroidManifestTemplate(featureModulePath).generate()
    StringTemplate(featureModulePath).generate()

    if (settings.presentationViewModel.hasActivity) {
      ActivityLayoutTemplate(featureModulePath).generate()
      presentationActivity.generate()
    }

    if (settings.presentationViewModel.hasFragment) {
      FragmentLayoutTemplate(featureModulePath).generate()
      presentationFragment.generate()
    }

    presentationBuildGradle.generate()
    presentationViewModel.generate()
    if (listOfUserInteraction.isNotEmpty()) {
      ViewInteractionTemplate(featureModulePath).generate()
    }
  }


  private fun setUpDataTemplates(dataModulePath: ModuleFilePath) {
    dataServiceGeneratorImplTemplate = RepositoryTemplate(dataModulePath)
    dataDataSourceTemplateGenerator = DataSourceInterfaceTemplate(dataModulePath)
    dataLocalDataSourceTemplateGenerator = LocalDataSourceTemplate(dataModulePath)
    dataRemoteDataSourceTemplateGenerator = RemoteDataSourceTemplate(dataModulePath)
    dataRetrofitAPITemplateGenerator = RetrofitAPITemplate(dataModulePath)
  }

  private fun setUpDomainTemplates(domainModulePath: ModuleFilePath) {
    domainEntityTemplateGenerator = EmptyEntityTemplate(domainModulePath)
    domainServiceGenerator = ServiceInterfaceTemplate(domainModulePath)
  }

  private fun setUpFeatureTemplates(featureModulePath: ModuleFilePath) {
    presentationBuildGradle = GradleModuleTemplate(featureModulePath)
    presentationActivity = ActvityTemplate(featureModulePath)
    presentationFragment = FragmentTemplate(featureModulePath)
    presentationViewModel = ViewModelTemplate(featureModulePath)
  }

  /**
   * Function to facilitate when creating features.
   */
  companion object {
    fun newFeature(
      settings: FeatureSettings,
      featureName: FeatureName,
      block: FeatureGenerator.() -> Unit = {}
    ) {
      val feature = FeatureGenerator(settings, featureName)
      feature.block()
      feature.generate()
    }
  }
}

/**
 * Iterate on each use case to facilitate routines of generating code with it.
 */
inline fun foreachUseCase(block: (useCase: UseCaseBuilder) -> Unit) {
  FeatureContext.featureGenerator.listOfUseCases.forEach {
    block(it)
  }
}

/**
 * Iterate on each liveData to facilitate routines of generating code with it.
 */
inline fun foreachLiveData(block: (useCase: LiveDataBuilder) -> Unit) {
  FeatureContext.featureGenerator.listOfLiveData.forEach {
    block(it)
  }
}

/**
 * Iterate on each ComponentState to facilitate routines of generating code with it.
 */
inline fun foreachComponentState(block: (useCase: ComponentStateBuilder) -> Unit) {
  FeatureContext.featureGenerator.listOfComponentState.forEach {
    block(it)
  }
}

/**
 * Iterate on each UserInteraction to facilitate routines of generating code with it.
 */
inline fun foreachUserInteraction(block: (useCase: UserInteractionBuilder) -> Unit) {
  FeatureContext.featureGenerator.listOfUserInteraction.forEach {
    block(it)
  }
}

fun printCustomTypeImport(output: PrintWriter) {
  FeatureContext.featureGenerator.listOfUseCases
    .map { it.parameters }
    .takeIf { it.isNotEmpty() }
    ?.reduce { acc, list ->
      mutableListOf<Parameter>().apply {
        addAll(acc)
        addAll(list)
      }
    }?.apply {
      customTypeImport(output, this)
    }
}