package com.mctech.architecture.generator.generator.entity

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.GlobalSettings
import java.io.File

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
abstract class EntityGenerator(val modulePath: ModuleFilePath) : FileGenerator, FilePath {
    val featureName = GlobalSettings.currentFeatureName
    val packageLine = modulePath.packageValue.getPackageLine() + "." + featureName.toSegmentalName() + ".entity"

    override fun getPath(): String {
        return modulePath.getPath() + featureName.toSegmentalName() + "/entity/" + featureName.toEntityName() + ".kt"
    }
}