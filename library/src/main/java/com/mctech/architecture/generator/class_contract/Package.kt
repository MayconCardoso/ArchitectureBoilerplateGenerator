package com.mctech.architecture.generator.class_contract

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
data class Package(val value: String) {
    fun getSegmentedPackage() = value.replace(".", "/")
    fun getPackageLine() = "package $value"
    fun getImportLine() = "import $value"
}