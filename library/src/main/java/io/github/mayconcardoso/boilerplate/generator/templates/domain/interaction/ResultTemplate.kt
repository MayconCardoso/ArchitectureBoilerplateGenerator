package io.github.mayconcardoso.boilerplate.generator.templates.domain.interaction

import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.core.generator.printTabulate
import io.github.mayconcardoso.boilerplate.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO
 */
open class ResultTemplate :
  KotlinTemplate(FeatureContext.featureGenerator.domainModulePath) {
  override val folder: String
    get() = ""

  override val className: String
    get() = "Result"

  override fun generateImports(output: PrintWriter) = Unit
  override fun generateClassName(output: PrintWriter) {
    output.println("sealed class Result<out T> {")
  }

  override fun generateClassBody(output: PrintWriter) {
    output.printTabulate("data class Success<T>(val result: T) : Result<T>()")
    output.printTabulate("data class Failure(val throwable: Throwable) : Result<Nothing>()")
  }
}