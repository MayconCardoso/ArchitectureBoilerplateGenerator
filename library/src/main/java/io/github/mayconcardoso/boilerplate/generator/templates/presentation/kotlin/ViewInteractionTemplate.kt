package io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.customTypeImport
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.entityPackage
import io.github.mayconcardoso.boilerplate.generator.core.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.core.generator.printImport
import io.github.mayconcardoso.boilerplate.generator.core.generator.printTabulate
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO
 */
open class ViewInteractionTemplate(modulePath: ModuleFilePath) :
  PresentationKotlinTemplate(modulePath) {

  private val interactions = FeatureContext.featureGenerator.listOfUserInteraction

  override val className: String
    get() = "${featureEntityName()}UserInteraction"

  override fun generateImports(output: PrintWriter) {
    val baseArchitecturePackage =
      FeatureContext.featureGenerator.baseArchitecturePath.packageValue.getImportLine()
    output.printImport("$baseArchitecturePackage.UserInteraction")

    if (interactions.any { it.hasGeneratedEntity() }) {
      output.printImport("${entityPackage()}.${featureEntityName()}")
    }

    interactions
      .filter { it.parameters.isNotEmpty() }
      .map { it.parameters }
      .forEach {
        customTypeImport(output, it)
      }

    output.blankLine()
  }

  override fun generateClassName(output: PrintWriter) {
    output.println("sealed class $className: UserInteraction{")
  }

  override fun generateClassBody(output: PrintWriter) {
    interactions.forEach { it ->
      if (it.parameters.isEmpty()) {
        output.printTabulate("object ${it.name} : $className()")
      } else {
        output.printTabulate("data class ${it.name}(")
        output.println(
          it.parameters
            .map {
              "\t\tval ${it.name} : ${it.type.getType()},\n"
            }
            .reduce { acc, useCaseVariable ->
              acc + useCaseVariable
            }
            .removeSuffix(",\n")
        )
        output.printTabulate(") : $className()")
      }

      output.blankLine()
    }
  }
}