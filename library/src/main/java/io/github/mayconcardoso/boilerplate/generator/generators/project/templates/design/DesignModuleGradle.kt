package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.PresentationFramework
import io.github.mayconcardoso.boilerplate.generator.templates.Template

class DesignModuleGradle(
  private val presentationFramework: PresentationFramework,
) : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/libraries/design/build.gradle"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
apply plugin: 'com.android.library'
apply plugin: "kotlin-android"

${printComposeAndroidSettings()}

dependencies {
    implementation libraries.googleMaterialDesign
    ${printComposeDependencies()}
}
""".trimIndent()
    )
  }

  private fun printComposeAndroidSettings(): String {
    return if (presentationFramework == PresentationFramework.Compose) {
      """
      android {
          buildFeatures {
              compose true
          }
          composeOptions {
              kotlinCompilerExtensionVersion '1.4.3'
          }
      }
      """.trimIndent()
    } else {
      ""
    }
  }

  private fun printComposeDependencies(): String {
    return if (presentationFramework == PresentationFramework.Compose) {
      """implementation libraries.googleMaterialDesign
    implementation libraries.androidComposeUi
    implementation libraries.androidComposeMaterial
    implementation libraries.androidComposeToolingPreview
    debugImplementation libraries.androidComposeTooling"""
    } else {
      ""
    }
  }
}