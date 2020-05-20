package com.mctech.architecture.generator.builder

import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import java.util.*

/**
 * @author MAYCON CARDOSO on 2020-05-13.
 */
data class UserInteractionBuilder(
    val name: String,
    val parameters: List<Parameter> = listOf(),
    val connectedUseCase : UseCaseBuilder? = null,
    val connectedState : ComponentStateBuilder? = null
) {

    fun getMethodName() : String{
        val methodName = name.removeSuffix("Interaction")
        return methodName.substring(0, 1).toLowerCase(Locale.getDefault()) + methodName.substring(1) + "Interaction"
    }

    fun hasGeneratedEntity(): Boolean {
        return parameters.any { it.type is Type.GeneratedEntity }
                || parameters.any { it.type is Type.ListOfGeneratedEntity }
                || parameters.any { if (it.type is Type.ResultOf) it.type.type is Type.GeneratedEntity else false }
                || parameters.any { if (it.type is Type.ListOf) it.type.type is Type.GeneratedEntity else false }
    }

}