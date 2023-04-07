package io.github.mayconcardoso.boilerplate.generator.core.settings

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Package

/**
 * Declares the base project settings used to generate files.
 */
data class ProjectSettings(
  /**
   * Defines the project name.
   */
  val projectName: String = "",

  /**
   * Define where is your project. If you are using it as a library or even a module into your project,
   * you can use the default value. Otherwise set the absolute path where files have to be created.
   */
  val projectAbsolutePath: String = "app/",

  /**
   * This is the base package of your application.
   */
  val projectPackage: Package
)

/**
 *
 */
val baseProjectPath: String
  get() {
    if (GlobalSettings.projectSettings.projectAbsolutePath.endsWith("/")) {
      return GlobalSettings.projectSettings.projectAbsolutePath
    }
    return GlobalSettings.projectSettings.projectAbsolutePath + "/"
  }