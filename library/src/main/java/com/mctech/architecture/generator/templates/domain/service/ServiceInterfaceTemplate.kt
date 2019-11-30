package com.mctech.architecture.generator.templates.domain.service

import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class ServiceInterfaceTemplate(modulePath: ModuleFilePath) : Template(modulePath) {
    override val folder: String
        get() = "service"

    override val className: String
        get() = "${featureEntityName()}Service"

    override fun generate(output: PrintWriter) {
        output.println("interface $className")
    }
}