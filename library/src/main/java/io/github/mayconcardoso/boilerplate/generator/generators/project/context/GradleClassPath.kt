package io.github.mayconcardoso.boilerplate.generator.generators.project.context

/**
 * Defines the android targets for the current project.
 */
data class GradleClassPath(
  val withJacoco: Boolean,
  val withDaggerHilt: Boolean,
  val withNavigationSafeArgs: Boolean,
)

object DefaultGradleClassPath {
  operator fun invoke() = GradleClassPath(
    withJacoco = false,
    withDaggerHilt = false,
    withNavigationSafeArgs = false,
  )
}
