package com.mctech.architecture.generator.generator.entity

import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.path.FilePath

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
abstract class EntityGenerator(
    val modulePath : FilePath
) : FileGenerator