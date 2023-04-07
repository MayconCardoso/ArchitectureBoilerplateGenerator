package io.github.mayconcardoso.boilerplate.generator.settings

import io.github.mayconcardoso.boilerplate.generator.class_contract.Package

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
data class ProjectSettings(
  /**
   * Define where is your project. If you are using it as a library or even a module into your project,
   * you can use the default value.
   */
  val baseAndroidProjectPath: String = "",

  /**
   * This is the base package of your application.
   * e.g com.mctech.mobile.myApp
   */
  val basePackageName: Package
)

val baseProjectPath = GlobalSettings.projectSettings.baseAndroidProjectPath