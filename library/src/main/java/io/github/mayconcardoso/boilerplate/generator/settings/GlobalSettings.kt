package io.github.mayconcardoso.boilerplate.generator.settings

import io.github.mayconcardoso.boilerplate.generator.strategy.FileDuplicatedStrategy

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
object GlobalSettings {
  lateinit var projectSettings: ProjectSettings
  lateinit var fileDuplicatedStrategy: FileDuplicatedStrategy
}