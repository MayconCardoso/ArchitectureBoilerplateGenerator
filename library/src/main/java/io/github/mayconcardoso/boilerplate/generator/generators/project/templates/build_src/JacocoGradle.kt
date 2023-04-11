package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the jacoco android.
 */
class JacocoGradle : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/buildSrc/jacoco.gradle"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
apply plugin: "com.vanniktech.android.junit.jacoco"

junitJacoco {
    jacocoVersion = "0.8.8"
    excludes = [
            '**/*App.*',
            '**/*Application.*',
            '**/*Adapter.*',
            '**/adapter/**',
            '**/*ViewHolder.*',
            '**/*Activity.*',
            '**/*Fragment.*',
            '**/*Dialog.*',
            '**/R.class',
            '**/R${'$'}*.class',
            '**/BuildConfig.*',
            '**/Manifest*.*',
            'android/**/*.*',
            '**/*Test*.*',
            '**/testing/**',
            '**/di/**',
            '**/*Dagger.*',
            '**/architecture_testing/agent/*.*',
            'hilt_*/**',
            '**/databinding/**',
            '**/*inlined*.class'
    ]
}
      """.trimIndent()
    )
  }
}