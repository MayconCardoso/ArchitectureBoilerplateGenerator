package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.root

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the gradle properties file.
 */
class GradleProperties : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/gradle.properties"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
        kotlin.code.style=official
        android.useAndroidX=true
        android.nonTransitiveRClass=true
        org.gradle.jvmargs=-Xmx6g -XX:MaxPermSize=6g -XX:+UseParallelGC
        org.gradle.parallel=true
        org.gradle.vfs.watch=true
        org.gradle.caching=true
        org.gradle.unsafe.configuration-cache=true
      """.trimIndent()
    )
  }
}