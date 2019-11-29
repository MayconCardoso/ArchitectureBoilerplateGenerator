package com.mctech.architecture.generator.path

import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.class_contract.Package
import com.mctech.architecture.generator.settings.GlobalSettings

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ModuleFilePath(
    val packageValue: Package,
    val moduleName: String,
    val type: ModuleFilePathType = ModuleFilePathType.Java
) : FilePath {
    override fun getPath(): String {
        return "$moduleName/${type.folderName}/${packageValue.getSegmentedPackage()}/"
    }
}

sealed class ModuleFilePathType(val folderName: String) {
    /**
     * This is a JAVA module, so our path is 'src/main/java/....'
     */
    object Java : ModuleFilePathType("src/main/java/")

    /**
     * This is a KOTLIN module, so our path is 'src/main/java/....'
     */
    object Kotlin : ModuleFilePathType("src/main/kotlin/")
}

val projectPackage = GlobalSettings.projectSettings.basePackageName.value

sealed class ModuleDefaultLayers(val moduleFile: ModuleFilePath) {

    object Data : ModuleDefaultLayers(
        ModuleFilePath(
            moduleName = "data",
            packageValue = Package("$projectPackage.data")
        )
    )

    object Domain : ModuleDefaultLayers(
        ModuleFilePath(
            moduleName = "domain",
            packageValue = Package("$projectPackage.domain")
        )
    )

    object GeneratedFeature : ModuleDefaultLayers(
        ModuleFilePath(
            moduleName = "feature/${GlobalSettings.currentFeatureName.toSegmentalName()}",
            packageValue = Package("$projectPackage.${GlobalSettings.currentFeatureName.toSegmentalName()}")
        )
    )
}