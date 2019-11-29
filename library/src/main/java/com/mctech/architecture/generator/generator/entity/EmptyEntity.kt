package com.mctech.architecture.generator.generator.entity

import com.mctech.architecture.generator.alias.toEntityName
import com.mctech.architecture.generator.alias.toSegmentalName
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.ModuleFilePath

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class EmptyEntity(modulePath: ModuleFilePath) : EntityGenerator(modulePath) {

    override fun generate() = writeFile(this) { output ->
        // Write Package
        output.println(packageLine)

        // Write empty class
        output.println("")
        output.println("class ${featureName.toEntityName()}")
    }
}