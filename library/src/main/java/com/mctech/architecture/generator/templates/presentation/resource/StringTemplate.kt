package com.mctech.architecture.generator.templates.presentation.resource

import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureSegment

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class StringTemplate(private val moduleFilePath: ModuleFilePath) : FilePath, FileGenerator {
    override fun getPath(): String {
        return "features/feature-${featureSegment()}/${moduleFilePath.type.getResFolder()}values/strings.xml"
    }

    override fun generate() = writeFile(this) { output ->
        output.println("<resources>")
        output.blankLine()
        output.println("</resources>")
    }
}