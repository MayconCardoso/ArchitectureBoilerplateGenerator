package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the design module manifest.
 */
class DesignManifest : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/libraries/design/src/main/AndroidManifest.xml"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
    outputWriter.println("<manifest package=\"${GlobalSettings.projectSettings.projectPackage.value}.design\" />")
  }

}