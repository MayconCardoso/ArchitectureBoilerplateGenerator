package com.mctech.architecture.generator.templates.presentation.resource

import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.settings.featureSegment
import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class ActivityLayoutTemplate(private val moduleFilePath: ModuleFilePath) : FilePath, FileGenerator {
    override fun getPath(): String {
        return "Generating file: features/feature-${featureSegment()}/${moduleFilePath.type.getResFolder()}layout/activity_${featureEntityName().toLowerCase(Locale.getDefault())}.xml"
    }

    override fun generate() {
        println(getPath())
    }
}