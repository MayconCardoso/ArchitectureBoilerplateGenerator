package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the design module compose Typography.
 */
class DesignComposeTypography : Template() {

  private val projectPackage = GlobalSettings.projectSettings.projectPackage.value
  private val projectSegment = GlobalSettings.projectSettings.projectPackage.getSegmentedPackage()

  override fun getPath(): String {
    return "$baseProjectPath/libraries/design/src/main/java/${projectSegment}/design/compose/ProjectTypography.kt"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
      package ${projectPackage}.design.compose

      import androidx.compose.material.Typography
      import androidx.compose.runtime.Composable
      import androidx.compose.ui.text.TextStyle
      import androidx.compose.ui.text.font.FontWeight
      import androidx.compose.ui.unit.sp

      object TypographyFactory {
        @Composable
        operator fun invoke(colors: ProjectColors) = Typography(
          h1 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            letterSpacing = 0.sp
          ),
          h2 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            letterSpacing = 0.5.sp
          ),
          caption = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
          ),
          body1 = TextStyle(
            color = colors.textColorSecondary,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
          ),
          body2 = TextStyle(
            color = colors.textColorSecondary,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
          ),
        )
      }
      """.trimIndent()
    )
  }

}