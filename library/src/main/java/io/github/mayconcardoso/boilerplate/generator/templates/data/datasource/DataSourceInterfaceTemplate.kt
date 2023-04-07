package io.github.mayconcardoso.boilerplate.generator.templates.data.datasource

import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.serviceFeatureName
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.servicePackage
import io.github.mayconcardoso.boilerplate.generator.core.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO
 */
open class DataSourceInterfaceTemplate(modulePath: ModuleFilePath) :
  KotlinTemplate(modulePath, false) {
  override val folder: String
    get() = "datasource"

  override val className: String
    get() = "${featureEntityName()}DataSource"

  override fun generateImports(output: PrintWriter) {
    output.println("${servicePackage()}.${serviceFeatureName()}")
    output.blankLine()
  }

  override fun generateClassName(output: PrintWriter) {
    output.println("interface $className : ${serviceFeatureName()}")
  }

  override fun generateClassBody(output: PrintWriter) = Unit
}