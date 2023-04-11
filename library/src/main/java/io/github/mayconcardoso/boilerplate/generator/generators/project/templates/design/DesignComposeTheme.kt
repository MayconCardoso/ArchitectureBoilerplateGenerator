package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.design

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the design module compose Typography.
 */
class DesignComposeTheme : Template() {

  private val projectPackage = GlobalSettings.projectSettings.projectPackage.value
  private val projectSegment = GlobalSettings.projectSettings.projectPackage.getSegmentedPackage()

  override fun getPath(): String {
    return "$baseProjectPath/libraries/design/src/main/java/${projectSegment}/design/compose/ProjectComposeSettings.kt"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
      package ${projectPackage}.design.compose
      
      import androidx.compose.foundation.isSystemInDarkTheme
      import androidx.compose.material.MaterialTheme
      import androidx.compose.runtime.Composable
      import androidx.compose.runtime.CompositionLocalProvider
      import androidx.compose.runtime.ProvidableCompositionLocal
      import androidx.compose.material.Typography
      import androidx.compose.runtime.staticCompositionLocalOf
      
      /**
       * Declares the app theme provider with colors and typographies.
       */
      object ProjectComposeSettings {
      
        /**
         * Holds all available colors on the app.
         */
        lateinit var LocalColors: ProvidableCompositionLocal<ProjectColors>
      
        /**
         * Holds all the available typographies on the app.
         */
        lateinit var LocalTypography: ProvidableCompositionLocal<Typography>
      
        /**
         * Application composable theme.
         */
        @Composable
        fun ProjectTheme(
          isDarkMode: Boolean = isSystemInDarkTheme(),
          content: @Composable () -> Unit
        ) {
          // Sets the color pallet based on system theme.
          val colors = if (isDarkMode) darkColors else lightColors
          val materialColors = if (isDarkMode) darkMaterialColors else lightMaterialColors
      
          // Sets the project typography.
          val typography = TypographyFactory(colors = colors)
      
          // Sets the project providers.
          LocalColors = staticCompositionLocalOf { colors }
          LocalTypography = staticCompositionLocalOf { typography }
      
          // Sets the composition local providers with colors and typographies
          MaterialTheme(colors = materialColors, typography = typography) {
            CompositionLocalProvider(
              LocalColors provides colors,
              LocalTypography provides typography,
              content = content
            )
          }
        }
      }
      
      @Composable
      fun colorProvider() = ProjectComposeSettings.LocalColors.current
      
      @Composable
      fun typoProvider() = ProjectComposeSettings.LocalTypography.current
      """.trimIndent()
    )
  }

}