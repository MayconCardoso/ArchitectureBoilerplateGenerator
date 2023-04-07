package io.github.mayconcardoso.boilerplate.generator.generators.feature.path

import io.github.mayconcardoso.boilerplate.generator.core.class_contract.Package
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.path.projectPackage
import io.github.mayconcardoso.boilerplate.generator.core.settings.featurePackage
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureSegment


/**
 * These are the defaults layers implementation of the project.
 * You can change it when generating your feature by changing the layers variables.
 */
sealed class ModuleDefaultLayers(val moduleFile: ModuleFilePath) {

  object Data : ModuleDefaultLayers(
    ModuleFilePath(
      moduleLocation = "data",
      gradleModuleName = ":data",
      packageValue = Package("$projectPackage.data")
    )
  )

  object Domain : ModuleDefaultLayers(
    ModuleFilePath(
      moduleLocation = "domain",
      gradleModuleName = ":domain",
      packageValue = Package("$projectPackage.domain")
    )
  )

  object BaseArchitecture : ModuleDefaultLayers(
    ModuleFilePath(
      moduleLocation = "",
      gradleModuleName = "",
      packageValue = Package("com.mctech.architecture.mvvm.x.core")
    )
  )

  object GeneratedFeature : ModuleDefaultLayers(
    ModuleFilePath(
      moduleLocation = "${featureSegment()}",
      gradleModuleName = ":features:${featureSegment()}",
      packageValue = Package("$projectPackage${featurePackage()}")
    )
  )
}