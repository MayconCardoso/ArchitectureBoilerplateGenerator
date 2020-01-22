package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.builder.foreachLiveData
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printDoubleTabulate
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.settings.featurePackage
import com.mctech.architecture.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
open class FragmentTemplate(modulePath: ModuleFilePath)  : PresentationKotlinTemplate(modulePath) {

    override val className: String
        get() = "${featureEntityName()}Fragment"

    override fun generateImports(output: PrintWriter) {
        output.printImport("import android.os.Bundle")
        output.printImport("import android.view.View")
        output.blankLine()

        val baseArchitecturePackage = FeatureContext.featureGenerator.baseArchitecturePath.packageValue.getImportLine()
        output.printImport("$baseArchitecturePackage.BaseFragment")
        output.printImport("$baseArchitecturePackage.ComponentState")
        output.printImport("$baseArchitecturePackage.ViewCommand")
        output.printImport("$baseArchitecturePackage.extentions.bindState")
        output.printImport("$baseArchitecturePackage.extentions.bindData")
        output.printImport("$baseArchitecturePackage.extentions.bindCommand")
        output.printImport("$baseArchitecturePackage.extentions.sharedViewModel")

        if(hasGeneratedEntity()){
            output.printImport("${entityPackage()}.${featureEntityName()}")
        }

        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class $className : BaseFragment() {")
    }

    override fun generateClassBody(output: PrintWriter) {
        val viewModelName = FeatureContext.featureGenerator.presentationViewModel.className

        output.printTabulate("private val viewModel : $viewModelName by sharedViewModel($viewModelName::class.java)")
        output.blankLine()

        output.printTabulate("override fun getLayoutId() = R.layout.fragment_${featurePackage().toLowerCase()}")
        output.blankLine()

        output.printTabulate("override fun onViewCreated(view: View, savedInstanceState: Bundle?) {")
        output.printDoubleTabulate("super.onViewCreated(view, savedInstanceState)")
        output.blankLine()

        output.printDoubleTabulate("bindCommand(viewModel){ handleCommand(it) }")
        output.blankLine()

        // Create observable to data.
        foreachLiveData {
            output.printDoubleTabulate("bindData(viewModel.${it.name}){ handle${it.getMethodName()}Data(it) }")
        }

        output.printTabulate("}")
        output.blankLine()

        // The command handler method
        output.printTabulate("private fun handleCommand(it: ViewCommand) {")
        output.printDoubleTabulate("when(it){")
        output.blankLine()
        output.printDoubleTabulate("}")
        output.printTabulate("}")
        output.blankLine()

        // Create observable to data handlers.
        foreachLiveData {
            output.printTabulate("private fun handle${it.getMethodName()}Data(it: ${it.type.getType()}) {")
            output.printDoubleTabulate("TODO()")
            output.printTabulate("}")
            output.blankLine()
        }
    }

    private fun hasGeneratedEntity() : Boolean{
        return FeatureContext.featureGenerator.listOfLiveData.any {
            it.hasGeneratedEntity()
        }
    }
}