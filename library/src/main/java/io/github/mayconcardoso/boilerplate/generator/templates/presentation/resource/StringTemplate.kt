package io.github.mayconcardoso.boilerplate.generator.templates.presentation.resource

import io.github.mayconcardoso.boilerplate.generator.generator.FileGenerator
import io.github.mayconcardoso.boilerplate.generator.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.path.FilePath
import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.settings.featureSegment

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
open class StringTemplate(private val moduleFilePath: ModuleFilePath) : FilePath, FileGenerator {
  override fun getPath(): String {
    return "${baseProjectPath}features/feature-${featureSegment()}/${moduleFilePath.type.getResFolder()}values/strings.xml"
  }

  override fun generate() = writeFile(this) { output ->
    output.println("<resources>")
    output.blankLine()
    output.println("</resources>")
  }
}