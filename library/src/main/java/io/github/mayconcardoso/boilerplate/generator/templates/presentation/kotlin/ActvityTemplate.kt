package io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin

import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.entityPackage
import io.github.mayconcardoso.boilerplate.generator.core.generator.*
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.core.settings.featurePackage
import io.github.mayconcardoso.boilerplate.generator.generators.feature.foreachComponentState
import io.github.mayconcardoso.boilerplate.generator.generators.feature.foreachLiveData
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter
import java.util.*

/**
 * @author MAYCON CARDOSO
 */
open class ActvityTemplate(modulePath: ModuleFilePath) : PresentationKotlinTemplate(modulePath) {

  override val className: String
    get() = "${featureEntityName()}Activity"

  override fun generateImports(output: PrintWriter) {
    output.printImport("import android.os.Bundle")
    output.blankLine()

    val baseArchitecturePackage =
      FeatureContext.featureGenerator.baseArchitecturePath.packageValue.getImportLine()
    output.printImport("import androidx.appcompat.app.AppCompatActivity")
    output.printImport("$baseArchitecturePackage.ComponentState")
    output.printImport("$baseArchitecturePackage.ViewCommand")
    output.printImport("$baseArchitecturePackage.ktx.bindState")
    output.printImport("$baseArchitecturePackage.ktx.bindData")
    output.printImport("$baseArchitecturePackage.ktx.bindCommand")

    if (hasGeneratedEntity()) {
      output.printImport("${entityPackage()}.${featureEntityName()}")
    }

    output.blankLine()
  }

  override fun generateClassName(output: PrintWriter) {
    output.println("class $className : AppCompatActivity() {")
  }

  override fun generateClassBody(output: PrintWriter) {
    val viewModelName = FeatureContext.featureGenerator.presentationViewModel.className

    output.printTabulate("private val viewModel : $viewModelName by daggerViewModel($viewModelName::class.java)")
    output.blankLine()

    output.printTabulate("override fun onCreate(savedInstanceState: Bundle?) {")
    output.printDoubleTabulate("super.onCreate(savedInstanceState)")
    output.printDoubleTabulate("setContentView(R.layout.activity_${featurePackage().lowercase(Locale.getDefault())})")
    output.blankLine()

    output.printDoubleTabulate("bindCommand(viewModel){ handleCommand(it) }")
    output.blankLine()

    // Create observable to data.
    foreachLiveData {
      output.printDoubleTabulate("bindData(viewModel.${it.name}){ handle${it.getMethodName()}Data(it) }")
    }

    // Create observable to states.
    foreachComponentState {
      output.printDoubleTabulate("bindState(viewModel.${it.name}){ handle${it.getMethodName()}State(it) }")
    }

    output.printTabulate("}")
    output.blankLine()

    // The command handler method
    output.printTabulate("private fun handleCommand(it: ViewCommand) {")
    output.printDoubleTabulate("when(it){")
    output.printTripleTabulate("//TODO()")
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


    // Create observable to states handlers.
    foreachComponentState {
      output.printTabulate("private fun handle${it.getMethodName()}State(state: ComponentState<${it.type.getType()}>) {")
      output.printDoubleTabulate("when(state){")
      output.printTripleTabulate("is ComponentState.Initializing -> TODO()")
      output.printTripleTabulate("is ComponentState.Loading -> TODO()")
      output.printTripleTabulate("is ComponentState.Error -> TODO()")
      output.printTripleTabulate("is ComponentState.Success -> TODO()")
      output.printDoubleTabulate("}")
      output.printTabulate("}")
      output.blankLine()
    }
  }

  private fun hasGeneratedEntity(): Boolean {
    return FeatureContext.featureGenerator.listOfLiveData.any {
      it.hasGeneratedEntity()
    } || FeatureContext.featureGenerator.listOfComponentState.any {
      it.hasGeneratedEntity()
    }
  }
}