package com.mctech.architecture.generator.templates.presentation.module

import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.generator.readFile
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.settings.GlobalSettings
import com.mctech.architecture.generator.settings.featureSegment
import com.mctech.architecture.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class AddFeatureOnSettingsFileTemplate : FilePath, FileGenerator {
    override fun getPath(): String {
        return GlobalSettings.projectSettings.baseAndroidProjectPath + "settings.gradle"
    }

    override fun generate() {
        val linesOfFile = readFile(this).toMutableList()

        val moduleName = "include 'features:feature-${featureSegment()}'"
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
}