package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the design module compose Colors.
 */
class DesignComposeColors : Template() {

  private val projectPackage = GlobalSettings.projectSettings.projectPackage.value
  private val projectSegment = GlobalSettings.projectSettings.projectPackage.getSegmentedPackage()

  override fun getPath(): String {
    return "$baseProjectPath/libraries/design/src/main/java/${projectSegment}/design/compose/ProjectColors.kt"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
      package ${projectPackage}.design.compose

      import android.graphics.Color.parseColor
      import androidx.compose.material.lightColors
      import androidx.compose.runtime.Immutable
      import androidx.compose.ui.graphics.Color
      
      @Immutable
      data class ProjectColors(
        val primary: Color,
        val primaryDark: Color,
        val primaryLight: Color,
        val accent: Color,
        val accentDark: Color,
        val accentLight: Color,
        val textColorPrimary: Color,
        val textColorSecondary: Color,
        val textColorInverted: Color,
      )
      
      /**
       * Declares the light colors of the app.
       */
      val lightColors: ProjectColors = ProjectColors(
        primary = Color(parseColor("#F44336")),
        primaryDark = Color(parseColor("#D32F2F")),
        primaryLight = Color(parseColor("#FFCDD2")),
      
        accent = Color(parseColor("#795548")),
        accentDark = Color(parseColor("#5D4037")),
        accentLight = Color(parseColor("#D7CCC8")),
      
        textColorPrimary = Color(parseColor("#000000")),
        textColorSecondary = Color(parseColor("#7F7F82")),
        textColorInverted = Color.White,
      )
      
      /**
       * Declares the light colors of the app.
       */
      val lightMaterialColors = lightColors(
        primary = lightColors.primary,
        primaryVariant = lightColors.primaryDark,
        secondary = lightColors.accent,
        secondaryVariant = lightColors.accentDark,
      )
      
      /**
       * Declares the dark colors of the app.
       * It will be defined in a future implementation.
       */
      val darkColors: ProjectColors = lightColors
      
      /**
       * Declares the dark colors of the app.
       * It will be defined in a future implementation.
       */
      val darkMaterialColors = lightMaterialColors
      """.trimIndent()
    )
  }

}