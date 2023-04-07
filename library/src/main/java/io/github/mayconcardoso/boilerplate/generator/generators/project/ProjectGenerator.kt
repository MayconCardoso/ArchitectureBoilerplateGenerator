package io.github.mayconcardoso.boilerplate.generator.generators.project

import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.ProjectSettings
import io.github.mayconcardoso.boilerplate.generator.core.strategy.FileDuplicatedStrategy

class ProjectGenerator private constructor(
  projectSettings: ProjectSettings,
  fileDuplicatedStrategy: FileDuplicatedStrategy
) {

  init {
    GlobalSettings.projectSettings = projectSettings
    GlobalSettings.fileDuplicatedStrategy = fileDuplicatedStrategy
  }

  companion object {
    fun generateEmptyProject(
      settings: ProjectSettings,
      fileDuplicatedStrategy: FileDuplicatedStrategy = FileDuplicatedStrategy.Replace,
      block: ProjectGenerator.() -> Unit,
    ) {
      val projectGenerator = ProjectGenerator(settings, fileDuplicatedStrategy)
      projectGenerator.block()
      projectGenerator.generate()
    }
  }

  private fun generate() {
    println("Hello world")
  }
}