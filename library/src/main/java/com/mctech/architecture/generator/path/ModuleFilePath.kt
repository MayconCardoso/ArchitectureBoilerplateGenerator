package com.mctech.architecture.generator.path

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ModuleFilePath(
    private val moduleName : String,
    private val type: ModuleFilePathType = ModuleFilePathType.Kotlin
) :
    FilePath {
    override fun getPath(): String {
        return "$moduleName/${type.folderName}"
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