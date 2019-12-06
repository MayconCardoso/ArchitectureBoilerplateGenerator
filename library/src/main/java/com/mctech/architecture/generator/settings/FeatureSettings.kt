package com.mctech.architecture.generator.settings

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.alias.toPackageName
import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
data class FeatureSettings(
    /**
     * The global project settings.
     */
    val projectSettings: ProjectSettings,

    /**
     * The duplicate file strategy. It says the generator how handle when a file that is being generated already exists.
     */
    var fileDuplicatedStrategy: FileDuplicatedStrategy = FileDuplicatedStrategy.Ignore,

    /**
     * When true the generator will create all of the use cases with @Inject annotation on the constructor.
     * So basically all of the UseCases will be auto-injectable by dagger.
     */
    var createDependencyInjectionModules: Boolean = false,

    /**
     * When true the generator will create both 'LocalDataSource' and 'RemoteDataSource' with the Retrofit API inside.
     * Beside of this, the repository file will handle both calls by delegating to these two data sources.
     */
    var createBothRemoteAndLocalDataSources: Boolean = true,

    /**
     * The wa
     */
    val presentationViewModel: PresentationMode
)

enum class PresentationMode(val hasActivity: Boolean, val hasFragment: Boolean) {
    /**
     * The activity class and its layout are gonna be generated.
     */
    Activity(true, false),

    /**
     * The fragment class and its layout are gonna be generated.
     */
    Fragment(false, true),

    /**
     * Both activity and fragment classes and their layout are gonna be generated.
     */
    ActivityAndFragment(true, true),

    /**
     * The generator will not create any visualization.
     */
    None(false, false)
}

/**
 * Return the domain entity name
 */
fun featureEntityName() = GlobalSettings.currentFeatureName.toEntityName()

/**
 * It is gonna return the segmental name of the feature.
 * For example, for the feature name 'TestCamelCase' will return 'test-camel-case'
 */
fun featureSegment() = GlobalSettings.currentFeatureName.toSegmentalName()

/**
 * It is gonna return the package name of the feature.
 * For example, for the feature name 'TestCamelCase' will return 'test_camel_case'
 */
fun featurePackage() = GlobalSettings.currentFeatureName.toPackageName()