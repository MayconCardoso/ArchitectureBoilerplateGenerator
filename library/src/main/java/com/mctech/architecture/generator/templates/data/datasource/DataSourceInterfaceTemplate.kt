package com.mctech.architecture.generator.templates.data.datasource

import com.mctech.architecture.generator.context.serviceFeatureName
import com.mctech.architecture.generator.context.servicePackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class DataSourceInterfaceTemplate(modulePath: ModuleFilePath) : Template(modulePath, false) {
    override val folder: String
        get() = "datasource"

    override val className: String
        get() = "${featureEntityName()}DataSource"

    override fun generateImports(output: PrintWriter) {
        output.println("${servicePackage()}.$serviceFeatureName")
        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("interface $className : $serviceFeatureName")
    }

    override fun generateClassBody(output: PrintWriter) = Unit
}