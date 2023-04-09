package io.github.mayconcardoso.boilerplate.generator.generators.project.context

/**
 * Defines the android targets for the current project.
 */
data class AndroidTargets(
  val minSdk: Int,
  val targetSdk: Int,
  val compileSdk: Int,
  val kotlinJvmTarget: Int,
)

object DefaultAndroidTarget {
  operator fun invoke() = AndroidTargets(
    minSdk = 21,
    targetSdk = 33,
    compileSdk = 33,
    kotlinJvmTarget = 11,
  )
}