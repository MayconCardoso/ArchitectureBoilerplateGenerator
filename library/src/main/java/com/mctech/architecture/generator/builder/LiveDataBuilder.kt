package com.mctech.architecture.generator.builder

import com.mctech.architecture.generator.class_contract.Type
import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
data class LiveDataBuilder(
    val name: String,
    val type: Type = Type.Unit
) {
    fun hasGeneratedEntity(): Boolean {
        return type is Type.GeneratedEntity || type is Type.ListOfGeneratedEntity
    }

    fun getMethodName(): String {
        return name.substring(0, 1).toUpperCase(Locale.getDefault()) + name.substring(1)
    }
}