package com.mctech.architecture.generator.templates.data

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.localDataSourcePacakge
import com.mctech.architecture.generator.context.remoteDataSourcePacakge
import com.mctech.architecture.generator.context.servicePackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class RepositoryTemplate(modulePath: ModuleFilePath) : Template(modulePath) {
    override val folder: String
        get() = ""

    override val className: String
        get() = "${featureEntityName()}Repository"

    override fun generate(output: PrintWriter) {
        val localDataSourceFeatureName = FeatureContext.featureGenerator.localDataSourceGenerator.className
        val remoteDataSourceFeatureName = FeatureContext.featureGenerator.remoteDataSourceGenerator.className

        output.println("${localDataSourcePacakge()}.$localDataSourceFeatureName")
        output.println("${remoteDataSourcePacakge()}.$remoteDataSourceFeatureName")
        output.blankLine()

        output.println("class $className(")
        output.println("\tprivate val localDataSource: $localDataSourceFeatureName,")
        output.println("\tprivate val remoteDataSource: $remoteDataSourceFeatureName")
        output.println(")")
    }
}