package io.github.mayconcardoso.boilerplate.generator.templates.presentation.resource

import io.github.mayconcardoso.boilerplate.generator.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.settings.featurePackage
import io.github.mayconcardoso.boilerplate.generator.settings.featureSegment
import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
open class ActivityLayoutTemplate(moduleFilePath: ModuleFilePath) :
  BaseLayoutTemplate(moduleFilePath) {
  override fun getPath(): String {
    return "${baseProjectPath}features/feature-${featureSegment()}/${moduleFilePath.type.getResFolder()}layout/activity_${
        featurePackage().lowercase(
            Locale.getDefault()
        )
    }.xml"
  }
}