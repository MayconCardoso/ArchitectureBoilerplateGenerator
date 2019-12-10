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
    val moduleLocation: String
) : FilePath {
    val type: ModuleFilePathType = ModuleFilePathType.Kotlin

    override fun getPath(): String {
        return "$moduleLocation/${type.folderName}/${packageValue.getSegmentedPackage()}/"
    }
}

sealed class ModuleFilePathType(val folderName: String) {

    fun getResFolder()      = "src/main/res/"
    fun getMainFolder()     = "src/main/"
    fun getSourceFolder()   = ""

    /**
     * This is a KOTLIN module, so our path is 'src/main/kotlin/....'
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
            packageValue = Package("${projectPackage}.data")
        )
    )

    object Domain : ModuleDefaultLayers(
        ModuleFilePath(
            moduleLocation = "domain",
            packageValue = Package("${projectPackage}.domain")
        )
    )

    object GeneratedFeature : ModuleDefaultLayers(
        ModuleFilePath(
            moduleLocation = "feature-${featureSegment()}",
            packageValue = Package("${projectPackage}.feature.${featurePackage()}")
        )
    )
}