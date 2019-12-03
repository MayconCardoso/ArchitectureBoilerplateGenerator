package com.mctech.architecture.generator.templates.data.datasource

import com.mctech.architecture.generator.builder.UseCaseBuilder
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.dataSourceFeatureName
import com.mctech.architecture.generator.context.entityPackage
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
abstract class BaseDataSourceImplementationTemplate(modulePath: ModuleFilePath) : Template(modulePath) {
    override val folder: String
        get() = "datasource"

    abstract fun createClassParameters(): String

    override fun generateImports(output: PrintWriter) {
        // There is a generated entity as a type return or a parameter.
        if (hasGeneratedEntity()) {
            output.printImport("${entityPackage()}.${featureEntityName()}")
            output.blankLine()
        }
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class ${className}${createClassParameters()} : $dataSourceFeatureName{")
        output.blankLine()
    }

    override fun generateClassBody(output: PrintWriter) {
        val useCases = FeatureContext.featureGenerator.listOfUseCases
        for (position in 0 until useCases.size) {
            createMethodSignature(useCases[position], output)
        }
    }

    private fun hasGeneratedEntity(): Boolean {
        return FeatureContext.featureGenerator.listOfUseCases.any {
            it.hasGeneratedEntity()
        }
    }

    private fun createMethodSignature(useCase: UseCaseBuilder, output: PrintWriter) {
        output.printTabulate("override suspend fun ${useCase.getMethodName()}${useCase.createParametersSignature()}${useCase.createReturnTypeForServices()}{")
        output.printTabulate(
            countTabulate = 2,
            value = "TODO()"
        )
        output.printTabulate("}")
        output.blankLine()
    }
}