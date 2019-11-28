package com.mctech.architecture.generator.alias

import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
typealias FeatureName = String

fun FeatureName.toEntityName() = this.toLowerCase(Locale.getDefault()).capitalize()
fun FeatureName.toSegmentalName() = this.toLowerCase(Locale.getDefault())