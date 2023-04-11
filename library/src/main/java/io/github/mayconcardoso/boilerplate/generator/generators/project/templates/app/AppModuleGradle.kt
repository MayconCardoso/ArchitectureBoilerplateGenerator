package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.app

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFileString
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.GradleClassPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.PresentationFramework
import io.github.mayconcardoso.boilerplate.generator.templates.Template

class AppModuleGradle(
  private val classPath: GradleClassPath,
  private val presentationFramework: PresentationFramework,
) : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/app/build.gradle"
  }

  override fun generate() = writeFileString(this) {
    """
apply plugin: 'com.android.application'
apply plugin: 'kotlin-kapt'
apply plugin: 'kotlin-android'
${printPlugins()}

${printAndroidSettings()}

dependencies {
    // Internal Libraries
    implementation project(path: ':libraries:design')

    // Navigation
    implementation libraries.androidNavigationFragmentKtx
    implementation libraries.androidNavigationUiKtx
    
    // Personal Libs
    implementation libraries.mvvmCore
    implementation libraries.mvvmCoreKtx
    testImplementation libraries.mvvmCoreTesting

    // UI
    implementation libraries.androidCoreKtx
    implementation libraries.androidAppCompat
    implementation libraries.googleMaterialDesign
    ${printComposeDependencies()}
    ${printDaggerDependencies()}
}
      """.trimIndent()
  }

  private fun printPlugins()= StringBuilder().apply {
    if(classPath.withDaggerHilt) {
      appendLine("apply plugin: \"dagger.hilt.android.plugin\"")
    }
    if (classPath.withNavigationSafeArgs) {
      appendLine("apply plugin: \"androidx.navigation.safeargs.kotlin\"")
    }
  }

  private fun printAndroidSettings(): String {
    val isCompose = presentationFramework == PresentationFramework.Compose
    return """
            android {
                defaultConfig {
                    applicationId = "${GlobalSettings.projectSettings.projectPackage.value}"
                    versionCode 1
                    versionName "1.0"
                }
            
                buildFeatures {
                    ${(if (isCompose) "compose true" else "")}
                    viewBinding true
                }
                ${
      (if (isCompose) """
                composeOptions {
                    kotlinCompilerExtensionVersion '1.4.3'
                }
                """ else "")
    }
            }
    """.trimIndent()
  }

  private fun printComposeDependencies() = StringBuilder().apply {
    if (presentationFramework == PresentationFramework.Compose) {
      appendLine("implementation libraries.androidComposeUi")
      appendLine("    implementation libraries.androidComposeMaterial")
      appendLine("    implementation libraries.androidComposeToolingPreview")
      appendLine("    debugImplementation libraries.androidComposeTooling")
    }
  }

  private fun printDaggerDependencies() = StringBuilder().apply {
    if (classPath.withDaggerHilt) {
      appendLine("// Dagger")
      appendLine("    implementation libraries.googleHiltAndroid")
      appendLine("    kapt libraries.googleHiltCompiler")
    }
  }
}