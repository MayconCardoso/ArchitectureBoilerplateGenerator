package io.github.mayconcardoso.boilerplate.generator.templates.data.datasource

import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName

/**
 * @author MAYCON CARDOSO
 */
open class LocalDataSourceTemplate(modulePath: ModuleFilePath) :
  BaseDataSourceImplementationTemplate(modulePath) {

  override val className: String
    get() = "Local${featureEntityName()}DataSource"

  override fun createClassParameters(): String = ""
}