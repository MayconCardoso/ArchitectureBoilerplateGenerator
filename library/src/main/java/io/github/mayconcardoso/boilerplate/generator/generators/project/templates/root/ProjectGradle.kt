package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.GradleClassPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the project gradle file with all plugins required to run the app.
 */
class ProjectGradle(
  private val gradleClassPath: GradleClassPath,
) : Template() {

  private val rootDirString = "\$rootDir"

  override fun getPath(): String {
    return "$baseProjectPath/build.gradle"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
      // Setup SDK versions.
      apply from: "$rootDirString/buildSrc/setup.gradle"
      
      // Setup project dependencies.
      apply from: "$rootDirString/buildSrc/dependencies.gradle"
      
      // Setup build script.
      buildscript {
          repositories {
              google()
              mavenCentral()
          }
      
          dependencies {
              classpath "com.android.tools.build:gradle:7.4.2"
              classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10"
              ${printDaggerClassPath()}
              ${printJacocoClassPath()}
              ${printNavigationArgsClassPath()}
          }
      }
      
      // Setup all supported repositories
      allprojects {
          repositories {
              google()
              mavenCentral()
          }
      }
      
      // Setup subprojects dependencies
      apply from: "$rootDirString/buildSrc/subprojects.gradle"
      
      ${printJacocoPlugin()}
      
      // Setup project tasks.
      task clean(type: Delete) {
          delete rootProject.buildDir
      }
      """.trimIndent()
    )
  }

  private fun printJacocoPlugin(): String {
    return if (gradleClassPath.withJacoco) {
      """
      // Setup JaCoCo plugin
            apply from: "$rootDirString/buildSrc/jacoco.gradle"
      """.trimIndent()
    } else {
      ""
    }
  }

  private fun printDaggerClassPath(): String {
    if (gradleClassPath.withDaggerHilt) {
      return "classpath \"com.google.dagger:hilt-android-gradle-plugin:2.45\""
    }

    return ""
  }

  private fun printJacocoClassPath(): String {
    if (gradleClassPath.withJacoco) {
      return "classpath \"com.vanniktech:gradle-android-junit-jacoco-plugin:0.16.0\""
    }

    return ""
  }

  private fun printNavigationArgsClassPath(): String {
    if (gradleClassPath.withNavigationSafeArgs) {
      return "classpath \"androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3\""
    }

    return ""
  }
}