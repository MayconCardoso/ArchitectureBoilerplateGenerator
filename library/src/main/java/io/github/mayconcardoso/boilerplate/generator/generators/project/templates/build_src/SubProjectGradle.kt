package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the subproject android task to set common variables.
 */
class SubProjectGradle : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/buildSrc/subprojects.gradle"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
      subprojects {
        afterEvaluate { project ->
            if (project.hasProperty('android')) {
                android {
                    compileSdk targets.compileSdk
                    defaultConfig {
                        minSdk targets.minSdk
                        targetSdk targets.targetSdk
                        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
                    }
                    java {
                        sourceCompatibility = JavaVersion.VERSION_11
                        targetCompatibility = JavaVersion.VERSION_11
                    }
                    compileOptions {
                        sourceCompatibility JavaVersion.VERSION_11
                        targetCompatibility JavaVersion.VERSION_11
                    }
                    kotlinOptions {
                        jvmTarget = targets.kotlinJvmTarget
                    }
                    packagingOptions {
                        exclude 'META-INF/*'
                    }
                }
            }
        }
      }
      """.trimIndent()
    )
  }
}