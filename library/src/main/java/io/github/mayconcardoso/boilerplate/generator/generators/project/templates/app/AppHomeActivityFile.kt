package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.app

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFileString
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

class AppHomeActivityFile : Template() {

  private val projectSegment = GlobalSettings.projectSettings.projectPackage.getSegmentedPackage()
  private val projectPackage = GlobalSettings.projectSettings.projectPackage.value

  override fun getPath(): String {
    return "$baseProjectPath/app/src/main/java/${projectSegment}/HomeActivity.kt"
  }

  override fun generate() = writeFileString(this) {
    """
package $projectPackage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.mayconcardoso.mvvm.core.ktx.viewBinding
import ${projectPackage}.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

  private val binding by viewBinding(ActivityHomeBinding::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
  }

}
    """.trimIndent()
  }

}