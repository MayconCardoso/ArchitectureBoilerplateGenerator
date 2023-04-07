package io.github.mayconcardoso.boilerplate.generator.core.path

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Package
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings

/**
 * @author MAYCON CARDOSO
 */
class ModuleFilePath(
  val packageValue: Package,
  val gradleModuleName: String,
  val moduleLocation: String
) : FilePath {
  val type: ModuleFilePathType = ModuleFilePathType.Java

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