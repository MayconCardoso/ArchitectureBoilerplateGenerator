package com.mctech.architecture.generator.path

import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.settings.GlobalSettings

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
val defaultDataPath     = ModuleFilePath("data")
val defaultDomainPath   = ModuleFilePath("domain")
val generatedFeature    = ModuleFilePath("feature/${GlobalSettings.currentFeatureName.toSegmentalName()}")
