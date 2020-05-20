package com.mctech.architecture.generator.class_contract

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.context.FeatureContext
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

    object Long : Type() {
        override fun getType() = "Long"
    }

    object Float : Type() {
        override fun getType() = "Float"
    }

    object Double : Type() {
        override fun getType() = "Double"
    }

    object Boolean : Type() {
        override fun getType() = "Boolean"
    }

    object GeneratedEntity : Type() {
        override fun getType() = FeatureContext.featureGenerator.featureName.toEntityName()
    }

    object ListOfGeneratedEntity : Type() {
        override fun getType() = "List<${FeatureContext.featureGenerator.featureName.toEntityName()}>"
    }

    data class ListOf(val type : Type) : Type(){
        override fun getType() = "List<${type.getType()}>"
    }

    data class ResultOf(val type : Type) : Type(){
        override fun getType() = "Result<${type.getType()}>"
    }

    data class CustomType(val packageValue : kotlin.String, val typeReturn : kotlin.String) : Type(){
        override fun getType() = typeReturn
    }
}

fun customTypeImport(output : PrintWriter, list: List<Parameter>){
    val types = mutableListOf<String>()

    list.filter { it.type is Type.CustomType }.forEach {
        val importValue = "import ${(it.type as Type.CustomType).packageValue}.${it.type.typeReturn}"

        if(!types.contains(importValue)){
            output.println(importValue)
            types.add(importValue)
        }
    }
}