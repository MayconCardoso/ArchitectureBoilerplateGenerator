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

fun entityPackage()                 = FeatureContext.featureGenerator.domainEntityTemplateGenerator.getPackage().getImportLine()
fun servicePackage()                = FeatureContext.featureGenerator.domainServiceGenerator.getPackage().getImportLine()
fun dataSourcePackage()             = FeatureContext.featureGenerator.dataDataSourceTemplateGenerator.getPackage().getImportLine()
fun localDataSourcePackage()        = FeatureContext.featureGenerator.dataLocalDataSourceTemplateGenerator.getPackage().getImportLine()
fun remoteDataSourcePackage()       = FeatureContext.featureGenerator.dataRemoteDataSourceTemplateGenerator.getPackage().getImportLine()
fun retrofitAPIPackage()            = FeatureContext.featureGenerator.dataRetrofitAPITemplateGenerator.getPackage().getImportLine()

fun serviceFeatureName()            = FeatureContext.featureGenerator.domainServiceGenerator.className
fun dataSourceFeatureName()         = FeatureContext.featureGenerator.dataDataSourceTemplateGenerator.className
fun localDataSourceFeatureName()    = FeatureContext.featureGenerator.dataLocalDataSourceTemplateGenerator.className
fun remoteDataSourceFeatureName()   = FeatureContext.featureGenerator.dataRemoteDataSourceTemplateGenerator.className
fun retrofitApiFeatureName()        = FeatureContext.featureGenerator.dataRetrofitAPITemplateGenerator.className