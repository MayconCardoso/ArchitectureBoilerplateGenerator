package io.github.mayconcardoso.boilerplate.generator.templates.domain.entity

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.customTypeImport
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.core.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO
 */
open class EmptyEntityTemplate(modulePath: ModuleFilePath) : KotlinTemplate(modulePath, false) {

  override val folder: String
    get() = "entity"

  override val className: String
    get() = featureEntityName()

  private val fields = FeatureContext.featureGenerator.listOfFieldsOnEntity

  override fun generateImports(output: PrintWriter) {
    customTypeImport(output, fields)
    output.blankLine()
  }

  override fun generateClassName(output: PrintWriter) {
    // There is no field.
    if (FeatureContext.featureGenerator.listOfFieldsOnEntity.isEmpty()) {
      output.println("class $className")
      return
    }

    // Open lass..
    output.println("data class $className(")

    // Print fields.
    output.println(
      FeatureContext.featureGenerator.listOfFieldsOnEntity
        .map {
          "\tval ${it.name} : ${it.type.getType()},\n"
        }
        .reduce { acc, useCaseVariable ->
          acc + useCaseVariable
        }
        .removeSuffix(",\n")
    )

    // Close class
    output.println(")")
  }

  override fun generateClassBody(output: PrintWriter) = Unit
}