package com.mctech.architecture.generator.templates.presentation.module

import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureSegment

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class GradleModuleTemplate(private val moduleFilePath: ModuleFilePath) : FilePath, FileGenerator {
    override fun getPath(): String {
        return "Generating file: features/feature-${featureSegment()}/build.gradle"
    }

    override fun generate() {
        println(getPath())
    }
}