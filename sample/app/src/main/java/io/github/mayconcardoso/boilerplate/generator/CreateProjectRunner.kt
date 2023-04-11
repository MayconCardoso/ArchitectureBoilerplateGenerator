package io.github.mayconcardoso.boilerplate.generator

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Package
import io.github.mayconcardoso.boilerplate.generator.core.settings.ProjectSettings
import io.github.mayconcardoso.boilerplate.generator.core.strategy.FileDuplicatedStrategy
import io.github.mayconcardoso.boilerplate.generator.generators.project.ProjectGenerator
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.AndroidTargets
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.GradleClassPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.PresentationFramework

fun main() {

  // Creates base project settings.
  val projectSettings = ProjectSettings(
    projectName = "MyGeneratedProject",
    projectPackage = Package("io.github.mayconcardoso.my_generated_project"),
    projectAbsolutePath = "C:\\Users\\mayco\\Documents\\Development\\MyGeneratedProject"
  )

  // Defines the duplication strategy
  val duplicatedStrategy = FileDuplicatedStrategy.Replace

  // Creates project generator
  ProjectGenerator.generateEmptyProject(
    settings = projectSettings,
    fileDuplicatedStrategy = duplicatedStrategy,
  ) {

    withGradleClassPath {
      GradleClassPath(
        withJacoco = true,
        withDaggerHilt = true,
        withNavigationSafeArgs = true,
      )
    }

    withCustomAndroidTargets {
      AndroidTargets(
        minSdk = 21,
        targetSdk = 33,
        compileSdk = 33,
        kotlinJvmTarget = 11,
      )
    }

    withPresentationFramework {
      PresentationFramework.Compose
    }
  }

}