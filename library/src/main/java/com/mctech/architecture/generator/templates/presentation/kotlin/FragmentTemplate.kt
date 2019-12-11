package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.*
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.settings.featurePackage
import com.mctech.architecture.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class FragmentTemplate(modulePath: ModuleFilePath)  : PresentationKotlinTemplate(modulePath) {

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

        output.printDoubleTabulate("bindCommand(viewModel){")
        output.printTripleTabulate("handleCommand(it)")
        output.printDoubleTabulate("}")

        output.printTabulate("}")
        output.blankLine()

        // The command handler method
        output.printTabulate("private fun handleCommand(it: ViewCommand) {")
        output.printDoubleTabulate("when(it){")
        output.blankLine()
        output.printDoubleTabulate("}")
        output.printTabulate("}")
        output.blankLine()
    }
}