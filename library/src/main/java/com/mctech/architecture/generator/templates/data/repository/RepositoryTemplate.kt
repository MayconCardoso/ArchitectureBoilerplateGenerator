package com.mctech.architecture.generator.templates.data.repository

import com.mctech.architecture.generator.builder.UseCaseBuilder
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.context.*
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printImport
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

    override fun generateImports(output: PrintWriter) {
        output.printImport("${servicePackage()}.$serviceFeatureName")
        output.printImport("${localDataSourcePackage()}.$localDataSourceFeatureName")

        if (FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources) {
            output.printImport("${remoteDataSourcePackage()}.$remoteDataSourceFeatureName")
        }

        // There is a generated entity as a type return or a parameter.
        if (hasGeneratedEntity()) {
            output.printImport("${entityPackage()}.${featureEntityName()}")
        }

        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        doesTheClassHaveBody = FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources

        // Class name
        output.println("class ${className}(")

        // Local dataSource
        output.printTabulate("private val localDataSource: $localDataSourceFeatureName" + getCommaOrNot())

        // Remote datSource
        if (FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources) {
            output.printTabulate("private val remoteDataSource: $remoteDataSourceFeatureName")
        }

        output.println(") : ${serviceFeatureName}${createDelegationWhenRemoteDataSourceDoesNotExist()}")
    }

    private fun createDelegationWhenRemoteDataSourceDoesNotExist(): String {
        if (FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources) {
            return "{"
        }

        return " by localDataSource"
    }

    override fun generateClassBody(output: PrintWriter) {
        if (!FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources){
            return
        }

        output.blankLine()

        val useCases = FeatureContext.featureGenerator.listOfUseCases
        for (position in 0 until useCases.size) {
            createMethodSignature(useCases[position], output)
        }
    }

    private fun createMethodSignature(useCase: UseCaseBuilder, output: PrintWriter) {
        output.printTabulate("override suspend fun ${useCase.getMethodName()}${useCase.createParametersSignature()}${useCase.createReturnTypeForServices()}{")

        if(useCase.returnType is Type.Unit){
            createMethodBodyWithoutReturn(useCase, output)
        }
        else{
            createMethodBodyWithReturn(useCase, output)
        }

        output.printTabulate("}")
        output.blankLine()
    }

    private fun createMethodBodyWithReturn(useCase: UseCaseBuilder, output: PrintWriter) {
        output.printTabulate(
            countTabulate = 2,
            value = "return try{"
        )

        output.printTabulate(
            countTabulate = 3,
            value = "remoteDataSource.${useCase.getMethodName()}${useCase.createParametersAsParameter()}.apply {"
        )

        output.printTabulate(
            countTabulate = 4,
            value = "TODO(\"Here you must call the local dataSource to save it on cache\")"
        )

        output.printTabulate(
            countTabulate = 3,
            value = "}"
        )

        output.printTabulate(
            countTabulate = 2,
            value = "} catch (ex : Exception){"
        )

        output.printTabulate(
            countTabulate = 3,
            value = "localDataSource.${useCase.getMethodName()}${useCase.createParametersAsParameter()}"
        )

        output.printTabulate(
            countTabulate = 2,
            value = "}"
        )
    }

    private fun createMethodBodyWithoutReturn(useCase: UseCaseBuilder, output: PrintWriter) {
        output.printTabulate(
            countTabulate = 2,
            value = "try{"
        )

        output.printTabulate(
            countTabulate = 3,
            value = "remoteDataSource.${useCase.getMethodName()}${useCase.createParametersAsParameter()}.apply {"
        )

        output.printTabulate(
            countTabulate = 4,
            value = "localDataSource.${useCase.getMethodName()}${useCase.createParametersAsParameter()}"
        )

        output.printTabulate(
            countTabulate = 3,
            value = "}"
        )

        output.printTabulate(
            countTabulate = 2,
            value = "} catch (ex : Exception){"
        )

        output.printTabulate(
            countTabulate = 3,
            value = "TODO(\"Handle the error here\")"
        )

        output.printTabulate(
            countTabulate = 2,
            value = "}"
        )
    }

    private fun getCommaOrNot() =
        if (FeatureContext.featureGenerator.settings.createBothRemoteAndLocalDataSources)
            ","
        else
            ""
    private fun hasGeneratedEntity(): Boolean {
        return FeatureContext.featureGenerator.listOfUseCases.any {
            it.hasGeneratedEntity()
        }
    }
}