package com.mctech.architecture.generator.path

import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.settings.GlobalSettings
import com.mctech.architecture.generator.settings.featurePackage
import com.mctech.architecture.generator.settings.featureSegment

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ModuleFilePath(
    val packageValue: Package,
    val gradleModuleName : String,
    val moduleLocation: String
) : FilePath {
    val type: ModuleFilePathType = ModuleFilePathType.Java

    override fun getPath(): String {
        return "$moduleLocation/${type.folderName}/${packageValue.getSegmentedPackage()}/"
    }
}

sealed class ModuleFilePathType(val folderName: String) {

    fun getResFolder()      = "src/main/res/"
    fun getMainFolder()     = "src/main/"
    fun getSourceFolder()   = ""

    /**
     * This is a JAVA module, so our path is 'src/main/java/....'
     */
    object Java : ModuleFilePathType("src/main/java/")

    /**
     * This is a KOTLIN module, so our path is 'src/main/java/....'
     */
    object Kotlin : ModuleFilePathType("src/main/kotlin/")
}


// This is the base package of the architecture.
val projectPackage = GlobalSettings.projectSettings.basePackageName.value

/**
 * These are the defaults layers implementation of the project.
 * You can change it when generating your feature by changing the layers variables.
 */
sealed class ModuleDefaultLayers(val moduleFile: ModuleFilePath) {

    object Data : ModuleDefaultLayers(
        ModuleFilePath(
            moduleLocation = "data",
            gradleModuleName = ":data",
            packageValue = Package("$projectPackage.data")
        )
    )

    object Domain : ModuleDefaultLayers(
        ModuleFilePath(
            moduleLocation = "domain",
            gradleModuleName = ":domain",
            packageValue = Package("$projectPackage.domain")
        )
    )

    object BaseArchitecture : ModuleDefaultLayers(
        ModuleFilePath(
            moduleLocation = "libraries/library-shared-feature-arq",
            gradleModuleName = ":libraries:library-shared-feature-arq",
            packageValue = Package("$projectPackage.feature.arq")
        )
    )

    object GeneratedFeature : ModuleDefaultLayers(
        ModuleFilePath(
            moduleLocation = "feature-${featureSegment()}",
            gradleModuleName = ":features:feature-${featureSegment()}",
            packageValue = Package("$projectPackage.feature.${featurePackage()}")
        )
    )
}