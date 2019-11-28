package com.mctech.architecture.generator.generator.usecase

import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.class_contract.Parameter
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.settings.GlobalSettings
import java.io.File

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
data class UseCaseGenerator(
    val modulePath: FilePath,
    val name: String,
    val returnType: Type = Type.Unit,
    val parameters: List<Parameter> = listOf()
) : FileGenerator {

    override fun generate() {
        val featureName = GlobalSettings.currentFeatureName

        println(
            "UseCase generated: " +
                    File(
                        modulePath.getPath() + featureName.toSegmentalName() + "/interactor/" + name + "Case.kt"
                    ).absolutePath
        )
    }

}