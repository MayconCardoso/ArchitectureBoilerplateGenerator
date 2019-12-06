package com.mctech.architecture.generator.templates.presentation.resource

import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featurePackage
import com.mctech.architecture.generator.settings.featureSegment
import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class FragmentLayoutTemplate(moduleFilePath: ModuleFilePath) : BaseLayoutTemplate(moduleFilePath) {
    override fun getPath(): String {
        return "features/feature-${featureSegment()}/${moduleFilePath.type.getResFolder()}layout/fragment_${featurePackage().toLowerCase(
            Locale.getDefault()
        )}.xml"
    }
}