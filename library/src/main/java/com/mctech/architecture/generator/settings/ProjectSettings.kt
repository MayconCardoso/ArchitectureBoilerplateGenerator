package com.mctech.architecture.generator.settings

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ProjectSettings constructor(
    val baseAndroidProjectPath : String = "",
    val basePackageName : String,
    val isTheProjectModularized : Boolean = false
){
    init {
        if(!isTheProjectModularized){
            throw RuntimeException("The library does not support monolithic architecture yet.")
        }
    }
}