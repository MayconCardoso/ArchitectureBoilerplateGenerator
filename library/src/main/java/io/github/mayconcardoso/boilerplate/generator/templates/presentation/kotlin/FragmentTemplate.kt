package io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin

import io.github.mayconcardoso.boilerplate.generator.core.generator.*
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.core.settings.featurePackage
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.entityPackage
import io.github.mayconcardoso.boilerplate.generator.generators.feature.foreachComponentState
import io.github.mayconcardoso.boilerplate.generator.generators.feature.foreachLiveData
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter
import java.util.*

/**
 * @author MAYCON CARDOSO
 */
open class FragmentTemplate(modulePath: ModuleFilePath) : PresentationKotlinTemplate(modulePath) {

  override val className: String
    get() = "${featureEntityName()}Fragment"

  override fun generateImports(output: PrintWriter) {
    output.printImport("import android.os.Bundle")
    output.printImport("import android.view.View")
    output.printImport("import android.view.LayoutInflater")
    output.printImport("import android.view.ViewGroup")
    output.blankLine()

    val baseArchitecturePackage =
      FeatureContext.featureGenerator.baseArchitecturePath.packageValue.getImportLine()
    output.printImport("import androidx.fragment.app.Fragment")
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
    output.println("class $className : Fragment() {")
  }

  override fun generateClassBody(output: PrintWriter) {
    val viewModelName = FeatureContext.featureGenerator.presentationViewModel.className

    output.printTabulate("private val viewModel : $viewModelName by sharedViewModel($viewModelName::class.java)")
    output.blankLine()

    output.printTabulate("override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {")
    output.printDoubleTabulate(
      "return inflater.inflate(R.layout.fragment_${
        featurePackage().lowercase(
          Locale.getDefault()
        )
      }, container, false)"
    )
    output.printTabulate("}")
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

    // Create observable to states.
    foreachComponentState {
      output.printDoubleTabulate("bindState(viewModel.${it.name}){ handle${it.getMethodName()}State(it) }")
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