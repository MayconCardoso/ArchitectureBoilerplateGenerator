package com.mctech.architecture.generator.templates.presentation.module

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.readFile
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.settings.baseProjectPath
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy
import com.mctech.architecture.generator.templates.Template

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class AddFeatureOnSettingsFileTemplate : Template() {

    private val moduleName by lazy {
        "include '${FeatureContext.featureGenerator.featureModulePath.gradleModuleName}'"
    }
    private val linesOfFile by lazy {
        readFile(this).toMutableList()
    }

    override fun getPath(): String {
        return baseProjectPath + "settings.gradle"
    }

    override fun generate() {
        // Add new module on the of the file.
        if (linesOfFile.contains(moduleName).not()) {
            linesOfFile.add(moduleName)
        }

        // Print
        writeFile(this, FileDuplicatedStrategy.Replace) { output ->
            linesOfFile.forEach { line ->
                output.println(line)
            }
        }
    }

    fun containsFeature() : Boolean {
        return linesOfFile.contains(moduleName)
    }
}