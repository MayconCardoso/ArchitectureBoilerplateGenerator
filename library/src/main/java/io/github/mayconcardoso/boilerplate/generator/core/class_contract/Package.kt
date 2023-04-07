package io.github.mayconcardoso.boilerplate.generator.core.class_contract

/**
 * @author MAYCON CARDOSO
 */
data class Package(var value: String) {

  init {
    value = value.removePrefix("package").trim()
  }

  fun getSegmentedPackage() = value.replace(".", "/")

  fun getImportLine() = "import $value"

  fun getPackageLine() = "package $value"
}