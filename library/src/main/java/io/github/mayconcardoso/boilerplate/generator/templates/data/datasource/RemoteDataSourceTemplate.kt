package io.github.mayconcardoso.boilerplate.generator.templates.data.datasource

import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.retrofitAPIPackage
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.retrofitApiFeatureName
import io.github.mayconcardoso.boilerplate.generator.core.generator.printImport
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO
 */
open class RemoteDataSourceTemplate(modulePath: ModuleFilePath) :
  BaseDataSourceImplementationTemplate(modulePath) {

  override val className: String
    get() = "Remote${featureEntityName()}DataSource"

  override fun createClassParameters(): String = "(private val api : ${featureEntityName()}API)"

  override fun generateImports(output: PrintWriter) {
    output.printImport("${retrofitAPIPackage()}.${retrofitApiFeatureName()}")
    super.generateImports(output)
  }
}