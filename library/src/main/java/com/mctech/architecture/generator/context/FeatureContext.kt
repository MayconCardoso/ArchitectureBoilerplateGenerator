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

fun entityPackage()             = FeatureContext.featureGenerator.entityTemplateGenerator.getPackage().getImportLine()
fun servicePackage()            = FeatureContext.featureGenerator.serviceGenerator.getPackage().getImportLine()
fun dataSourcePackage()         = FeatureContext.featureGenerator.dataSourceTemplateGenerator.getPackage().getImportLine()
fun localDataSourcePacakge()    = FeatureContext.featureGenerator.localDataSourceTemplateGenerator.getPackage().getImportLine()
fun remoteDataSourcePacakge()   = FeatureContext.featureGenerator.remoteDataSourceTemplateGenerator.getPackage().getImportLine()


val serviceFeatureName          = FeatureContext.featureGenerator.serviceGenerator.className
val localDataSourceFeatureName  = FeatureContext.featureGenerator.localDataSourceTemplateGenerator.className
val remoteDataSourceFeatureName = FeatureContext.featureGenerator.remoteDataSourceTemplateGenerator.className