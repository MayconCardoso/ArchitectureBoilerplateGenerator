package io.github.mayconcardoso.boilerplate.generator.templates.data.datasource

import io.github.mayconcardoso.boilerplate.generator.context.retrofitAPIPackage
import io.github.mayconcardoso.boilerplate.generator.context.retrofitApiFeatureName
import io.github.mayconcardoso.boilerplate.generator.generator.printImport
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.featureEntityName
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
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