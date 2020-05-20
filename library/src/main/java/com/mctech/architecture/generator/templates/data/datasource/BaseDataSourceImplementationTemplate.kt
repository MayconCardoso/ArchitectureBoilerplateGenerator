package com.mctech.architecture.generator.templates.data.datasource

import com.mctech.architecture.generator.builder.UseCaseBuilder
import com.mctech.architecture.generator.builder.foreachUseCase
import com.mctech.architecture.generator.builder.printCustomTypeImport
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.dataSourceFeatureName
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printDoubleTabulate
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
abstract class BaseDataSourceImplementationTemplate(modulePath: ModuleFilePath) : KotlinTemplate(modulePath) {
    override val folder: String
        get() = "datasource"

    abstract fun createClassParameters(): String

    override fun generateImports(output: PrintWriter) {
        // Print custom types
        printCustomTypeImport(output)

        // There is a generated entity as a type return or a parameter.
        if (hasGeneratedEntity()) {
            output.printImport("${entityPackage()}.${featureEntityName()}")
            output.blankLine()
        }
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class ${className}${createClassParameters()} : ${dataSourceFeatureName()}{")
        output.blankLine()
    }

    override fun generateClassBody(output: PrintWriter) {
        foreachUseCase {
            createMethodSignature(it, output)
        }
    }

    private fun hasGeneratedEntity(): Boolean {
        return FeatureContext.featureGenerator.listOfUseCases.any {
            it.hasGeneratedEntity()
        }
    }

    private fun createMethodSignature(useCase: UseCaseBuilder, output: PrintWriter) {
        output.printTabulate("override suspend fun ${useCase.getMethodName()}${useCase.createParametersSignature()}${useCase.createReturnTypeForServices()}{")
        output.printDoubleTabulate("TODO()")
        output.printTabulate("}")
        output.blankLine()
    }
}