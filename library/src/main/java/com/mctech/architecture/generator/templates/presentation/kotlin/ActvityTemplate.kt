package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ActvityTemplate(modulePath: ModuleFilePath) : Template(modulePath, false) {

    override val folder: String
        get() = "entity"

    override val className: String
        get() = "${featureEntityName()}ViewModel"

    override fun generateImports(output: PrintWriter) = Unit

    override fun generateClassName(output: PrintWriter) {
        output.println("class $className")
    }

    override fun generateClassBody(output: PrintWriter) = Unit
}