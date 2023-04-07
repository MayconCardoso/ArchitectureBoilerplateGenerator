package io.github.mayconcardoso.boilerplate.generator.templates.domain.entity

import io.github.mayconcardoso.boilerplate.generator.class_contract.customTypeImport
import io.github.mayconcardoso.boilerplate.generator.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
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