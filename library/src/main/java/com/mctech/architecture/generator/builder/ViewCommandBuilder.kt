package com.mctech.architecture.generator.builder

import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type

/**
 * @author MAYCON CARDOSO on 2020-05-13.
 */
data class ViewCommandBuilder(
    val name: String,
    val parameters: List<Parameter> = listOf()
) {

    fun hasGeneratedEntity(): Boolean {
        return parameters.any { it.type is Type.GeneratedEntity }
                || parameters.any { it.type is Type.ListOfGeneratedEntity }
                || parameters.any { if (it.type is Type.ResultOf) it.type.type is Type.GeneratedEntity else false }
                || parameters.any { if (it.type is Type.ListOf) it.type.type is Type.GeneratedEntity else false }
    }

}