package com.mctech.architecture.generator.generator.usecase

import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.path.FilePath

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
data class UseCase(
    val modulePath: FilePath,
    val name: String,
    val returnType: Type = Type.Unit,
    val parameters: List<Parameter> = listOf(),
    val isDaggerInjectable : Boolean = true
)