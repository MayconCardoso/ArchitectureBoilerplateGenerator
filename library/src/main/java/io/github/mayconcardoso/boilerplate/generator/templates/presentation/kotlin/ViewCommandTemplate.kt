package io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin

import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO
 */
open class ViewCommandTemplate(modulePath: ModuleFilePath) : KotlinTemplate(modulePath, false) {

  override val folder: String
    get() = "entity"

  override val className: String
    get() = "${featureEntityName()}ViewModel"

  override fun generateImports(output: PrintWriter) = Unit

  override fun generateClassName(output: PrintWriter) {
    output.println("class $className")
  }

  override fun generateClassBody(output: PrintWriter) = Unit
}