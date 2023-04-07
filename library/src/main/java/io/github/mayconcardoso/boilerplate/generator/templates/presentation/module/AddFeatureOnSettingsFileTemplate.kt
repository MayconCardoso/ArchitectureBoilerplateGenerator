package io.github.mayconcardoso.boilerplate.generator.templates.presentation.module

import io.github.mayconcardoso.boilerplate.generator.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.generator.readFile
import io.github.mayconcardoso.boilerplate.generator.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.strategy.FileDuplicatedStrategy
import io.github.mayconcardoso.boilerplate.generator.templates.Template

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

  fun containsFeature(): Boolean {
    return linesOfFile.contains(moduleName)
  }
}