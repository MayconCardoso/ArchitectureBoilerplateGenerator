package com.mctech.architecture.generator.templates.data.datasource

import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
open class LocalDataSourceTemplate(modulePath: ModuleFilePath) :
    BaseDataSourceImplementationTemplate(modulePath) {

    override val className: String
        get() = "Local${featureEntityName()}DataSource"

    override fun createClassParameters(): String = ""
}