package io.github.mayconcardoso.boilerplate.generator.generators.project

import io.github.mayconcardoso.boilerplate.generator.generators.project.context.*
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.app.*
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src.DependenciesGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src.JacocoGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src.SubProjectGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src.TargetSetupGradle
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design.*
import io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root.*
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
    GradleWrapperProperties(),

    // BuildSrc
    DependenciesGradle(),
    SubProjectGradle(),
    TargetSetupGradle(androidTarget),

    // Design module
    DesignManifest(),
    DesignModuleGradle(presentationFramework),

    // App module
    AppManifest(),
    AppStringFile(),
    AppApplicationFile(gradleClassPath),
    AppModuleGradle(gradleClassPath, presentationFramework),
    AppHomeActivityFile(),
    AppHomeActivityLayoutFile(),
  )

  /**
   * Builds all files that will be generated.
   */
  fun buildFilesToGenerate(): List<Template> = buildList {
    // Includes all static files
    addAll(buildStaticFiles())

    // Includes compose files if it is enabled
    if (presentationFramework == PresentationFramework.Compose) {
      add(DesignComposeTheme())
      add(DesignComposeColors())
      add(DesignComposeTypography())
    }

    // Includes jacoco
    if (gradleClassPath.withJacoco) {
      add(JacocoGradle())
    }
  }

}