package com.mctech.architecture.generator.class_contract

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.settings.GlobalSettings
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
sealed class Type {
    abstract fun getType() : kotlin.String

    object Unit : Type() {
        override fun getType() = ""
    }

    object String : Type() {
        override fun getType() = "String"
    }

    object Int : Type() {
        override fun getType() = "Int"
    }

    object Float : Type() {
        override fun getType() = "Float"
    }

    object GeneratedEntity : Type() {
        override fun getType() = GlobalSettings.currentFeatureName.toEntityName()
    }

    object ListOfGeneratedEntity : Type() {
        override fun getType() = "List<${GlobalSettings.currentFeatureName.toEntityName()}>"
    }

    data class ResultOf(val type : Type) : Type(){
        override fun getType() = "Result<${type.getType()}>"
    }

    data class CustomType(val packageValue : kotlin.String, val typeReturn : kotlin.String) : Type(){
        override fun getType() = typeReturn
    }
}

fun customTypeImport(output : PrintWriter, list: List<Parameter>){
    list.filter { it.type is Type.CustomType }.forEach {
        output.println("import ${(it.type as Type.CustomType).packageValue}" )
    }
}