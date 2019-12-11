package com.mctech.architecture.generator.templates.data.api

import com.mctech.architecture.generator.builder.foreachUseCase
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class RetrofitAPITemplate(modulePath: ModuleFilePath) : KotlinTemplate(modulePath) {
    override val folder: String
        get() = "api"

    override val className: String
        get() = "${featureEntityName()}API"

    override fun generateImports(output: PrintWriter) {
        // There is a generated entity as a type return or a parameter.
        if (hasGeneratedEntity()) {
            output.printImport("${entityPackage()}.${featureEntityName()}")
            output.blankLine()
        }
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("interface $className{")
    }

    override fun generateClassBody(output: PrintWriter) {
        foreachUseCase { useCase ->
            output.printTabulate("fun ${useCase.getMethodName()}${useCase.createParametersSignature()}${useCase.createReturnTypeForServices()}")
        }
    }

    private fun hasGeneratedEntity(): Boolean {
        return FeatureContext.featureGenerator.listOfUseCases.any {
            it.hasGeneratedEntity()
        }
    }
}