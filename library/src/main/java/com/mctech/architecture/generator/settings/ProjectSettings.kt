package com.mctech.architecture.generator.settings

import com.mctech.architecture.generator.class_contract.Package

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
data class ProjectSettings (
    val baseAndroidProjectPath : String = "",
    val basePackageName : Package,
    val isTheProjectModularized : Boolean = false
){
    init {
        if(!isTheProjectModularized){
            throw RuntimeException("The library does not support monolithic architecture yet.")
        }
    }
}