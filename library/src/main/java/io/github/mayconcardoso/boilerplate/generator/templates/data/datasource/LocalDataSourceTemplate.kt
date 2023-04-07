package io.github.mayconcardoso.boilerplate.generator.templates.data.datasource

import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.featureEntityName

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
open class LocalDataSourceTemplate(modulePath: ModuleFilePath) :
  BaseDataSourceImplementationTemplate(modulePath) {

  override val className: String
    get() = "Local${featureEntityName()}DataSource"

  override fun createClassParameters(): String = ""
}