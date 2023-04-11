package io.github.mayconcardoso.boilerplate.generator.generators.project

import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.ProjectSettings
import io.github.mayconcardoso.boilerplate.generator.core.strategy.FileDuplicatedStrategy
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.AndroidTargets
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.GradleClassPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.PresentationFramework
import java.io.File

/**
 * Project generator class used to customize the generated files.
 */
class ProjectGenerator private constructor(
  projectSettings: ProjectSettings,
  fileDuplicatedStrategy: FileDuplicatedStrategy
) {

  /**
   * Holds the current project context with everything need to build it.
   */
  private val context = ProjectGeneratorContext()

  init {
    GlobalSettings.projectSettings = projectSettings
    GlobalSettings.fileDuplicatedStrategy = fileDuplicatedStrategy
  }

  fun withCustomAndroidTargets(block: () -> AndroidTargets) {
    context.androidTarget = block()
  }

  fun withGradleClassPath(block: () -> GradleClassPath) {
    context.gradleClassPath = block()
  }

  fun withPresentationFramework(block: () -> PresentationFramework) {
    context.presentationFramework = block()
  }

  private fun generate() {
    // Creates main folder if it does not exits.
    val projectFolder = File(GlobalSettings.projectSettings.projectAbsolutePath).apply {
      mkdirs()
    }

    // Move static files that will be generated independent of any customization and add-ons.
    context.buildFilesToGenerate().forEach { file ->
      file.generate()
    }
  }

  companion object {
    fun generateEmptyProject(
      settings: ProjectSettings,
      fileDuplicatedStrategy: FileDuplicatedStrategy = FileDuplicatedStrategy.Replace,
      block: ProjectGenerator.() -> Unit = {},
    ) {
      val projectGenerator = ProjectGenerator(settings, fileDuplicatedStrategy)
      projectGenerator.block()
      projectGenerator.generate()
    }
  }
}