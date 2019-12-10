package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ActvityTemplate(modulePath: ModuleFilePath) : PresentationKotlinTemplate(modulePath) {

    override val folder: String
        get() = "presentation"

    override val className: String
        get() = "${featureEntityName()}Activity"

    override fun generateImports(output: PrintWriter) {
        output.printImport("import android.os.Bundle")
        output.printImport("import androidx.appcompat.app.AppCompatActivity")
        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class $className : AppCompatActivity() {")
    }

    override fun generateClassBody(output: PrintWriter) {
    }
}