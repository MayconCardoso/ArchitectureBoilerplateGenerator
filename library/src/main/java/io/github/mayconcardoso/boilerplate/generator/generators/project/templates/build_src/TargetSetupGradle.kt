package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.AndroidTargets
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the android target file where you can setup android versions.
 */
class TargetSetupGradle(
  private val androidTargets: AndroidTargets,
) : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/buildSrc/setup.gradle"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
        // Defines SDK variables.
        ext.targets = [
            minSdk                  : ${androidTargets.minSdk},
            compileSdk              : ${androidTargets.compileSdk},
            targetSdk               : ${androidTargets.targetSdk},
            kotlinJvmTarget         : ${androidTargets.kotlinJvmTarget},
        ]
      """.trimIndent()
    )
  }
}