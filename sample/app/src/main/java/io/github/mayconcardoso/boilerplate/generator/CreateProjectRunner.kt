package io.github.mayconcardoso.boilerplate.generator

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Package
import io.github.mayconcardoso.boilerplate.generator.generators.project.ProjectGenerator
import io.github.mayconcardoso.boilerplate.generator.core.settings.ProjectSettings

fun main() {

  // Creates base project settings.
  val projectSettings = ProjectSettings(
    projectName = "MyProjectTest",
    projectPackage = Package("io.github.mayconcardoso.MyTestApplication"),
    projectAbsolutePath = "C:\\Users\\mayco\\Documents\\Development\\MyTestApplication"
  )

  // Creates project generator
  ProjectGenerator.generateEmptyProject(settings = projectSettings) {

  }

}