package io.github.mayconcardoso.boilerplate.generator.core.path

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Package
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings

/**
 * Indicates the path for a module that will be generated.
 *
 * @property packageValue the module package.
 * @property gradleModuleName the module name.
 * @property moduleLocation the module location within the project.
 */
class ModuleFilePath(
  val packageValue: Package,
  val gradleModuleName: String,
  val moduleLocation: String,
  val type: ModuleFilePathType = ModuleFilePathType.Java
) : FilePath {

  /**
   * Returns the path of the codebase for the module withing the project.
   */
  override fun getPath(): String {
    return "$moduleLocation/${type.folderName}/${packageValue.getSegmentedPackage()}/"
  }

}

sealed class ModuleFilePathType(val folderName: String) {

  fun getResFolder() = "src/main/res/"
  fun getMainFolder() = "src/main/"
  fun getSourceFolder() = ""

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
val projectPackage = GlobalSettings.projectSettings.projectPackage.value