package io.github.mayconcardoso.boilerplate.generator.generators.project.context

/**
 * Indicates which presentation framework will be used on the app.
 */
sealed class PresentationFramework {

  /**
   * Presentation layer will be created using Android Compose
   */
  object Compose: PresentationFramework()

  /**
   * Presentation layer will be created using Android Legacy XML files.
   */
  object LegacyXml: PresentationFramework()
}