package io.github.mayconcardoso.boilerplate.generator.generators.feature.builder

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Parameter
import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Type
import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.core.path.FilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featurePackage
import java.util.*

/**
 * @author MAYCON CARDOSO
 */
data class UseCaseBuilder(
  val name: String,
  val returnType: Type = Type.Unit,
  val parameters: List<Parameter> = listOf(),
  val isDaggerInjectable: Boolean = true,
  var modulePath: FilePath? = null
) {
  fun getMethodName(): String {
    val methodName = name.removeSuffix("Case")
    return methodName.substring(0, 1).lowercase(Locale.getDefault()) + methodName.substring(1)
  }

  fun getImportPath(): String {
    return FeatureContext.featureGenerator.domainModulePath.packageValue.getImportLine() + ".${featurePackage()}.interaction.$name"
  }

  fun createDaggerInjectable(): String {
    if (isDaggerInjectable) {
      return " @Inject constructor"
    }

    return ""
  }

  fun hasGeneratedEntity(): Boolean {
    return returnType is Type.GeneratedEntity
        || returnType is Type.ListOfGeneratedEntity
        || (returnType is Type.ResultOf && returnType.type is Type.GeneratedEntity)
        || (returnType is Type.ResultOf && returnType.type is Type.ListOfGeneratedEntity)
        || (returnType is Type.ListOf && returnType.type is Type.GeneratedEntity)

        || parameters.any { it.type is Type.GeneratedEntity }
        || parameters.any { it.type is Type.ListOfGeneratedEntity }
        || parameters.any { if (it.type is Type.ResultOf) it.type.type is Type.GeneratedEntity else false }
        || parameters.any { if (it.type is Type.ListOf) it.type.type is Type.GeneratedEntity else false }
  }

  fun hasInteractionResultEntity(): Boolean {
    return returnType is Type.ResultOf
        || parameters.any { it.type is Type.ResultOf }
  }

  fun createReturnTypeForUseCases(): String {
    val optional = if (returnType is Type.ResultOf) "" else "?"

    if (returnType is Type.Unit) {
      return ""
    }
    return ": " + returnType.getType() + optional
  }

  fun createReturnTypeForServices(): String {
    val optional = if (returnType is Type.ResultOf) "" else "?"

    if (returnType is Type.Unit) {
      return ""
    }

    // Only use cases can have result types.
    if (returnType is Type.ResultOf) {
      return ": " + returnType.type.getType() + optional
    }
    return ": " + returnType.getType() + optional
  }

  fun createParametersSignature(): String {
    if (parameters.isEmpty()) {
      return "()"
    }

    var result = "("
    parameters.forEach {
      result = result.plus("${it.name}: ${it.type.getType()}, ")
    }
    result = result.removeSuffix(", ").plus(")")
    return result
  }

  fun createParametersAsParameter(): String {
    if (parameters.isEmpty()) {
      return "()"
    }

    var result = "("
    parameters.forEach {
      result = result.plus("${it.name}, ")
    }
    result = result.removeSuffix(", ").plus(")")
    return result
  }
}