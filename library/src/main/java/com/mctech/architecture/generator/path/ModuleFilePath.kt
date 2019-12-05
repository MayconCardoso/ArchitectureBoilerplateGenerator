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
    val moduleName: String,
    val type: ModuleFilePathType = ModuleFilePathType.Kotlin
) : FilePath {
    override fun getPath(): String {
        return "$moduleName/${type.folderName}/${packageValue.getSegmentedPackage()}/"
    }
}

sealed class ModuleFilePathType(val folderName: String) {

    fun getResFolder()      = "src/main/res/"
    fun getMainFolder()     = "src/main/"
    fun getSourceFolder()   = folderName

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
private val projectPackage = GlobalSettings.projectSettings.basePackageName.value

/**
 * These are the defaults layers implementation of the project.
 * You can change it when generating your feature by changing the layers variables.
 */
sealed class ModuleDefaultLayers(val moduleFile: ModuleFilePath) {

    object Data : ModuleDefaultLayers(
        ModuleFilePath(
            moduleName = "data",
            packageValue = Package("${projectPackage}.data")
        )
    )

    object Domain : ModuleDefaultLayers(
        ModuleFilePath(
            moduleName = "domain",
            packageValue = Package("${projectPackage}.domain")
        )
    )

    object GeneratedFeature : ModuleDefaultLayers(
        ModuleFilePath(
            moduleName = "feature-${featureSegment()}",
            packageValue = Package("${projectPackage}.feature.${featurePackage()}")
        )
    )
}