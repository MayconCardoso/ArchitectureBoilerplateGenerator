package io.github.mayconcardoso.boilerplate.generator.templates.data.datasource

import io.github.mayconcardoso.boilerplate.generator.builder.UseCaseBuilder
import io.github.mayconcardoso.boilerplate.generator.builder.foreachUseCase
import io.github.mayconcardoso.boilerplate.generator.builder.printCustomTypeImport
import io.github.mayconcardoso.boilerplate.generator.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.context.dataSourceFeatureName
import io.github.mayconcardoso.boilerplate.generator.context.entityPackage
import io.github.mayconcardoso.boilerplate.generator.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.generator.printDoubleTabulate
import io.github.mayconcardoso.boilerplate.generator.generator.printImport
import io.github.mayconcardoso.boilerplate.generator.generator.printTabulate
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.featureEntityName
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
abstract class BaseDataSourceImplementationTemplate(modulePath: ModuleFilePath) :
  KotlinTemplate(modulePath) {
  override val folder: String
    get() = "datasource"

  abstract fun createClassParameters(): String

  override fun generateImports(output: PrintWriter) {
    // Print custom types
    printCustomTypeImport(output)

    // There is a generated entity as a type return or a parameter.
    if (hasGeneratedEntity()) {
      output.printImport("${entityPackage()}.${featureEntityName()}")
      output.blankLine()
    }
  }

  override fun generateClassName(output: PrintWriter) {
    output.println("class ${className}${createClassParameters()} : ${dataSourceFeatureName()}{")
    output.blankLine()
  }

  override fun generateClassBody(output: PrintWriter) {
    foreachUseCase {
      createMethodSignature(it, output)
    }
  }

  private fun hasGeneratedEntity(): Boolean {
    return FeatureContext.featureGenerator.listOfUseCases.any {
      it.hasGeneratedEntity()
    }
  }

  private fun createMethodSignature(useCase: UseCaseBuilder, output: PrintWriter) {
    output.printTabulate("override suspend fun ${useCase.getMethodName()}${useCase.createParametersSignature()}${useCase.createReturnTypeForServices()}{")
    output.printDoubleTabulate("TODO()")
    output.printTabulate("}")
    output.blankLine()
  }
}