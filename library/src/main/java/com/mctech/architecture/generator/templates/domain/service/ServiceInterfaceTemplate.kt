package com.mctech.architecture.generator.templates.domain.service

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
open class ServiceInterfaceTemplate(modulePath: ModuleFilePath) : KotlinTemplate(modulePath) {
    override val folder: String
        get() = "service"

    override val className: String
        get() = "${featureEntityName()}Service"

    override fun generateImports(output: PrintWriter) {
        // There is a generated entity as a type return or a parameter.
        // It is gonna create an import line like this:
        // import your.package.here.newFeature
        if (hasGeneratedEntity()) {
            output.printImport("${entityPackage()}.${featureEntityName()}")
        }
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("interface ${className}{")
    }

    override fun generateClassBody(output: PrintWriter) {
        val useCases = FeatureContext.featureGenerator.listOfUseCases
        for (position in 0 until useCases.size) {
            val useCase = useCases[position]
            output.printTabulate("suspend fun ${useCase.getMethodName()}${useCase.createParametersSignature()}${useCase.createReturnTypeForServices()}")
        }
    }

    private fun hasGeneratedEntity(): Boolean {
        return FeatureContext.featureGenerator.listOfUseCases.any {
            it.hasGeneratedEntity()
        }
    }
}