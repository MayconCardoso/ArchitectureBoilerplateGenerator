package com.mctech.architecture.generator.templates.data

import com.mctech.architecture.generator.context.*
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class RepositoryTemplate(modulePath: ModuleFilePath) : Template(modulePath) {
    override val folder: String
        get() = "repository"

    override val className: String
        get() = "${featureEntityName()}Repository"

    override fun generate(output: PrintWriter) {
        output.println("${servicePackage()}.$serviceFeatureName")
        output.println("${localDataSourcePacakge()}.$localDataSourceFeatureName")

        if(FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources){
            output.println("${remoteDataSourcePacakge()}.$remoteDataSourceFeatureName")
        }
        output.blankLine()

        output.println("class $className(")
        output.printTabulate("private val localDataSource: $localDataSourceFeatureName" + getCommaOrNot())
        if(FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources){
            output.printTabulate("private val remoteDataSource: $remoteDataSourceFeatureName")
        }
        output.println(") : $serviceFeatureName")
    }

    private fun getCommaOrNot() = if(FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources)
        ","
    else
        ""
}