package com.mctech.architecture.generator.class_contract

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
data class Package(var value: String) {

    init {
        value = value.removePrefix("package").trim()
    }

    fun getSegmentedPackage() = value.replace(".", "/")

    fun getImportLine() = "import $value"

    fun getPackageLine() = "package $value"
}