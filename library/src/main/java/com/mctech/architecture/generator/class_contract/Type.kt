package com.mctech.architecture.generator.class_contract

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.settings.GlobalSettings

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
sealed class Type {
    abstract fun getType() : kotlin.String

    object Unit : Type() {
        override fun getType() = ""
    }

    object String : Type() {
        override fun getType() = " : String"
    }

    object Int : Type() {
        override fun getType() = " : Int"
    }

    object Float : Type() {
        override fun getType() = " : Float"
    }

    object GeneratedEntity : Type() {
        override fun getType() = " : ${GlobalSettings.currentFeatureName.toEntityName()}"
    }

    object ListOfGeneratedEntity : Type() {
        override fun getType() = " : List<${GlobalSettings.currentFeatureName.toEntityName()}>"
    }
}