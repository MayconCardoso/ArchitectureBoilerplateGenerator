package io.github.mayconcardoso.boilerplate.generator.templates.presentation.kotlin

import io.github.mayconcardoso.boilerplate.generator.class_contract.customTypeImport
import io.github.mayconcardoso.boilerplate.generator.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.context.entityPackage
import io.github.mayconcardoso.boilerplate.generator.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.generator.printImport
import io.github.mayconcardoso.boilerplate.generator.generator.printTabulate
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
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