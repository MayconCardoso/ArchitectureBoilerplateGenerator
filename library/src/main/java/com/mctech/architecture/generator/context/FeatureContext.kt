package com.mctech.architecture.generator.context

import com.mctech.architecture.generator.builder.FeatureGenerator

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
fun localDataSourcePackage()    = FeatureContext.featureGenerator.localDataSourceTemplateGenerator.getPackage().getImportLine()
fun remoteDataSourcePackage()   = FeatureContext.featureGenerator.remoteDataSourceTemplateGenerator.getPackage().getImportLine()
fun retrofitAPIPackage()        = FeatureContext.featureGenerator.retrofitAPITemplateGenerator.getPackage().getImportLine()

val serviceFeatureName          = FeatureContext.featureGenerator.serviceGenerator.className
val dataSourceFeatureName       = FeatureContext.featureGenerator.dataSourceTemplateGenerator.className
val localDataSourceFeatureName  = FeatureContext.featureGenerator.localDataSourceTemplateGenerator.className
val remoteDataSourceFeatureName = FeatureContext.featureGenerator.remoteDataSourceTemplateGenerator.className
val retrofitApiFeatureName      = FeatureContext.featureGenerator.retrofitAPITemplateGenerator.className