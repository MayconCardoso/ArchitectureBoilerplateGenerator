package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.AndroidTargets
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the settings gradle file with generated modules.
 */
class SettingsGradle : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/settings.gradle"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
      // Internal Modules.
      include ':app'
      include ':libraries:design'
      """.trimIndent()
    )
  }
}