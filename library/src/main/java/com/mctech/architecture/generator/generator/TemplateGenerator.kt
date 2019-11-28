package com.mctech.architecture.generator.generator

import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.templates.FileTemplate

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class TemplateGenerator(
    private val modulePath: FilePath,
    private val fileTemplate: FileTemplate
) : FileGenerator {
    override fun generate() {
        println(fileTemplate.getTemplate())
    }
}