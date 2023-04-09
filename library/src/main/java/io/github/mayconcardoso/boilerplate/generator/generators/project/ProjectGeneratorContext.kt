package io.github.mayconcardoso.boilerplate.generator.generators.project

import io.github.mayconcardoso.boilerplate.generator.generators.project.context.*
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src.DependenciesGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src.SubProjectGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src.TargetSetupGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design.DesignComposeColors
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design.DesignComposeTypography
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design.DesignManifest
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design.DesignModuleGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root.GitIgnore
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root.GradleProperties
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root.ProjectGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root.SettingsGradle
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Holds all customizations of the project that will be generated.
 */
class ProjectGeneratorContext {

  /**
   * Holds the project android target
   */
  var androidTarget: AndroidTargets = DefaultAndroidTarget()


  /**
   * Holds the project gradle class path
   */
  var gradleClassPath: GradleClassPath = DefaultGradleClassPath()


  /**
   * Holds the project gradle class path
   */
  var presentationFramework: PresentationFramework = PresentationFramework.Compose

  /**
   * Builds all static files that will be generated with the project independent of any customization.
   */
  private fun buildStaticFiles() = listOf(
    // Root
    GitIgnore(),
    ProjectGradle(gradleClassPath),
    SettingsGradle(),
    GradleProperties(),

    // BuildSrc
    DependenciesGradle(),
    SubProjectGradle(),
    TargetSetupGradle(androidTarget),

    // Design module
    DesignManifest(),
    DesignModuleGradle(presentationFramework),
  )

  /**
   * Builds all files that will be generated.
   */
  fun buildFilesToGenerate(): List<Template> = buildList {
    // Includes all static files
    addAll(buildStaticFiles())

    // Includes compose files if it is enabled
    if(presentationFramework == PresentationFramework.Compose) {
      add(DesignComposeColors())
      add(DesignComposeTypography())
    }
  }

}