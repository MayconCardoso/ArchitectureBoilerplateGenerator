package io.github.mayconcardoso.boilerplate.generator.templates.presentation

import io.github.mayconcardoso.boilerplate.generator.generator.printPackage
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.settings.featureSegment
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

abstract class PresentationKotlinTemplate(moduleFilePath: ModuleFilePath) :
  KotlinTemplate(moduleFilePath) {
  override val folder: String
    get() = "presentation"

  override fun getPath(): String {
    return "${baseProjectPath}features/feature-${featureSegment()}/${moduleFilePath.type.folderName}${moduleFilePath.packageValue.getSegmentedPackage()}/$className.kt"
  }

  override fun generatePackage(output: PrintWriter) {
    output.printPackage(moduleFilePath.packageValue.getPackageLine())
  }
}