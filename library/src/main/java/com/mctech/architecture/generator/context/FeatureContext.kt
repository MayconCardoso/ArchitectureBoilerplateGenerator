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

fun entityPackage()             = FeatureContext.featureGenerator.domainEntityTemplateGenerator.getPackage().getImportLine()
fun servicePackage()            = FeatureContext.featureGenerator.domainServiceGenerator.getPackage().getImportLine()
fun dataSourcePackage()         = FeatureContext.featureGenerator.dataDataSourceTemplateGenerator.getPackage().getImportLine()
fun localDataSourcePackage()    = FeatureContext.featureGenerator.dataLocalDataSourceTemplateGenerator.getPackage().getImportLine()
fun remoteDataSourcePackage()   = FeatureContext.featureGenerator.dataRemoteDataSourceTemplateGenerator.getPackage().getImportLine()
fun retrofitAPIPackage()        = FeatureContext.featureGenerator.dataRetrofitAPITemplateGenerator.getPackage().getImportLine()

val serviceFeatureName          = FeatureContext.featureGenerator.domainServiceGenerator.className
val dataSourceFeatureName       = FeatureContext.featureGenerator.dataDataSourceTemplateGenerator.className
val localDataSourceFeatureName  = FeatureContext.featureGenerator.dataLocalDataSourceTemplateGenerator.className
val remoteDataSourceFeatureName = FeatureContext.featureGenerator.dataRemoteDataSourceTemplateGenerator.className
val retrofitApiFeatureName      = FeatureContext.featureGenerator.dataRetrofitAPITemplateGenerator.className