package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.app

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFileString
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

class AppStringFile : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/app/src/main/res/values/strings.xml"
  }

  override fun generate() = writeFileString(this) {
    """
<resources>
    <string name="app_name">${GlobalSettings.projectSettings.projectName}</string>
</resources>
    """.trimIndent()
  }

}