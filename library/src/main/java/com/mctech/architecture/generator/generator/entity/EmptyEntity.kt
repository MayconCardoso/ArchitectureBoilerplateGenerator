package com.mctech.architecture.generator.generator.entity

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.settings.GlobalSettings
import java.io.File

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class EmptyEntity(
    modulePath: FilePath
) : EntityGenerator(modulePath) {
    override fun generate() {
        val featureName = GlobalSettings.currentFeatureName
        println(
            "Entity generated: " +
                    File(
                        modulePath.getPath() + featureName.toSegmentalName() + "/entity/" + featureName.toEntityName() + ".kt"
                    ).absolutePath
        )
    }
}