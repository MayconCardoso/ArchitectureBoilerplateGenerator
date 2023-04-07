package io.github.mayconcardoso.boilerplate.generator.settings

import io.github.mayconcardoso.boilerplate.generator.class_contract.Package

/**
 * Declares the base project settings used to generate files.
 */
data class ProjectSettings(

  /**
   * Define where is your project. If you are using it as a library or even a module into your project,
   * you can use the default value. Otherwise set the absolute path where files have to be created.
   */
  val baseAndroidProjectPath: String = "app/",

  /**
   * This is the base package of your application.
   * e.g com.mctech.mobile.myApp
   */
  val basePackageName: Package
)

/**
 *
 */
val baseProjectPath: String
  get() {
    if(GlobalSettings.projectSettings.baseAndroidProjectPath.endsWith("/")) {
      return GlobalSettings.projectSettings.baseAndroidProjectPath
    }
    return GlobalSettings.projectSettings.baseAndroidProjectPath + "/"
  }