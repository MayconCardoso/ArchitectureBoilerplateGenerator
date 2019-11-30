package com.mctech.architecture.generator.templates.data

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.servicePackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class DataSourceInterfaceTemplate(modulePath: ModuleFilePath) : Template(modulePath) {
    override val folder: String
        get() = "datasource"

    override val className: String
        get() = "${featureEntityName()}DataSource"

    override fun generate(output: PrintWriter) {
        val serviceTemplate = FeatureContext.featureGenerator.serviceGenerator.className

        output.println("${servicePackage()}.$serviceTemplate")
        output.blankLine()

        output.println("interface $className : $serviceTemplate")
    }
}