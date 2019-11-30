package com.mctech.architecture.generator.context

import com.mctech.architecture.generator.generator.FeatureGenerator

/**
 * @author MAYCON CARDOSO on 2019-11-30.
 */
class FeatureContext {
    companion object{
        lateinit var featureGenerator : FeatureGenerator
    }
}

fun entityPackage()             = FeatureContext.featureGenerator.entityGenerator.getPackage().getImportLine()
fun servicePackage()            = FeatureContext.featureGenerator.serviceGenerator.getPackage().getImportLine()
fun dataSourcePackage()         = FeatureContext.featureGenerator.dataSourceGenerator.getPackage().getImportLine()
fun localDataSourcePacakge()    = FeatureContext.featureGenerator.localDataSourceGenerator.getPackage().getImportLine()
fun remoteDataSourcePacakge()   = FeatureContext.featureGenerator.remoteDataSourceGenerator.getPackage().getImportLine()