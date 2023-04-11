package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.app

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFileString
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.GradleClassPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

class AppApplicationFile(
  private val classPath: GradleClassPath,
) : Template() {

  private val projectSegment = GlobalSettings.projectSettings.projectPackage.getSegmentedPackage()

  override fun getPath(): String {
    return "$baseProjectPath/app/src/main/java/${projectSegment}/ProjectApplication.kt"
  }

  override fun generate() = writeFileString(this) {
    """
package ${GlobalSettings.projectSettings.projectPackage.value}

import android.app.Application
${printDaggerHilt()}    
class ProjectApplication : Application()
    """.trimIndent()
  }

  private fun printDaggerHilt()= StringBuilder().apply {
    if(classPath.withDaggerHilt) {
      appendLine("import dagger.hilt.android.HiltAndroidApp")
      appendLine("")
      append("@HiltAndroidApp")
    }

  }

}