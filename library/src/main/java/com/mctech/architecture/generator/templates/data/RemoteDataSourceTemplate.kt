package com.mctech.architecture.generator.templates.data

import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class RemoteDataSourceTemplate(modulePath: ModuleFilePath) : Template(modulePath) {
    override val folder: String
        get() = "datasource"

    override val className: String
        get() = "Remote${featureEntityName()}DataSource"

    override fun generate(output: PrintWriter) {
        output.println("class $className")
    }
}