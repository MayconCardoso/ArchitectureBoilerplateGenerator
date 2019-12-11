package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.builder.foreachUseCase
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ViewModelTemplate(modulePath: ModuleFilePath) : PresentationKotlinTemplate(modulePath) {


    override val className: String
        get() = "${featureEntityName()}ViewModel"

    override fun generateImports(output: PrintWriter) {
        output.printImport("import androidx.lifecycle.LiveData")
        output.printImport("import androidx.lifecycle.MutableLiveData")
        output.printImport("import androidx.lifecycle.viewModelScope")
        output.printImport("import kotlinx.coroutines.launch")
        output.printImport("import java.util.*")
        output.printImport("import javax.inject.Inject")

        val defaultImport = FeatureContext.featureGenerator.domainModulePath.packageValue.getImportLine() + "."
        foreachUseCase {
            output.printImport(defaultImport + it.name)
        }

        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class $className")
    }

    override fun generateClassBody(output: PrintWriter) {

    }
}