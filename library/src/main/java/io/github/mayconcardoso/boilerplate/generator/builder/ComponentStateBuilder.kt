package io.github.mayconcardoso.boilerplate.generator.builder

import io.github.mayconcardoso.boilerplate.generator.class_contract.Type
import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
data class ComponentStateBuilder(
  val name: String,
  val type: Type = Type.Unit
) {
  fun hasGeneratedEntity(): Boolean {
    return type is Type.GeneratedEntity || type is Type.ListOfGeneratedEntity
  }

  fun getMethodName(): String {
    return name.substring(0, 1).uppercase(Locale.getDefault()) + name.substring(1)
  }
}