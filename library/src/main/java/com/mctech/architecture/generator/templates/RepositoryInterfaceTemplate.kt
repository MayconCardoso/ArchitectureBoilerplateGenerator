package com.mctech.architecture.generator.templates

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.settings.GlobalSettings

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class RepositoryInterfaceTemplate : FileTemplate {
    override fun getTemplate(): String {
        val featureEntity = GlobalSettings.currentFeatureName.toEntityName()

        return "interface $featureEntity" + "Service"
    }
}