package io.github.mayconcardoso.boilerplate.generator.templates.data.datasource

import io.github.mayconcardoso.boilerplate.generator.context.serviceFeatureName
import io.github.mayconcardoso.boilerplate.generator.context.servicePackage
import io.github.mayconcardoso.boilerplate.generator.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
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