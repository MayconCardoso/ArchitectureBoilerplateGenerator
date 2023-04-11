package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the gradle properties file.
 */
class GradleWrapperProperties : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/gradle-wrapper.properties"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
distributionBase=GRADLE_USER_HOME
distributionUrl=https\://services.gradle.org/distributions/gradle-7.5-bin.zip
distributionPath=wrapper/dists
zipStorePath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
      """.trimIndent()
    )
  }
}