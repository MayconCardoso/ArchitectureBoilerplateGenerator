package com.mctech.architecture.generator.templates.domain.entity

import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class EmptyEntityTemplate(modulePath: ModuleFilePath) : Template(modulePath) {

    override val folder: String
        get() = "entity"

    override val className: String
        get() = featureEntityName()

    override fun generate(output: PrintWriter) {
        output.println("class $className")
    }
}