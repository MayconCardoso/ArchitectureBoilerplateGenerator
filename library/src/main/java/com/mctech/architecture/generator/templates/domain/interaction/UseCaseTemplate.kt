package com.mctech.architecture.generator.templates.domain.interaction

import com.mctech.architecture.generator.builder.UseCaseBuilder
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.class_contract.customTypeImport
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.context.serviceFeatureName
import com.mctech.architecture.generator.context.servicePackage
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
open class UseCaseTemplate(val useCase: UseCaseBuilder, modulePath: ModuleFilePath) :
    KotlinTemplate(modulePath) {
    override val folder: String
        get() = "interaction"

    override val className: String
        get() = useCase.name

    override fun generateImports(output: PrintWriter) {
        // Print custom types
        customTypeImport(output, useCase.parameters)

        // Print dagger dependencies
        if (useCase.isDaggerInjectable) {
            output.printImport("import javax.inject.Inject")
        }

        // There is an interaction result as a type or a parameter.
        if(useCase.hasInteractionResultEntity()){
            // Create the result template
            ResultTemplate().generate()

            output.printImport(FeatureContext.featureGenerator.domainModulePath.packageValue.getImportLine() + ".Result")
        }

        // There is a generated entity as a type return or a parameter.
        if (useCase.hasGeneratedEntity()) {
            output.printImport("${entityPackage()}.${featureEntityName()}")
        }

        output.printImport("${servicePackage()}.$serviceFeatureName")
        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class ${className}${useCase.createDaggerInjectable()}(private val service : ${serviceFeatureName}){")
    }

    override fun generateClassBody(output: PrintWriter) {
        // Print execute method declaration
        generateExecutionMethodName(output)

        // Print execute method body
        generateExecutionMethodBody(output)
    }

    protected open fun generateExecutionMethodBody(output: PrintWriter) {
        // There is no return type.
        if (useCase.returnType is Type.Unit) {
            generateExecutionMethodBodyWithoutReturn(output)
        }

        // There is a return type.
        else{
            generateExecutionMethodBodyWithReturn(output)
        }

        // Print end of method body
        output.printTabulate("}")
    }

    protected open fun generateExecutionMethodBodyWithReturn(output: PrintWriter) {
        output.printTabulate(countTabulate = 2, value = "return try{")

        if(useCase.returnType is Type.ResultOf){
            output.printTabulate(
                countTabulate = 3,
                value = "Result.Success(service.${useCase.getMethodName()}${useCase.createParametersAsParameter()})"
            )
        }
        else{
            output.printTabulate(
                countTabulate = 3,
                value = "service.${useCase.getMethodName()}${useCase.createParametersAsParameter()}"
            )
        }
        output.printTabulate(countTabulate = 2, value = "}")
        output.printTabulate(countTabulate = 2, value = "catch (ex : Exception){")
        output.printTabulate(countTabulate = 3, value = "ex.printStackTrace()")
        if(useCase.returnType is Type.ResultOf){
            output.printTabulate(countTabulate = 3, value = "Result.Failure(ex)")
        }
        else{
            output.printTabulate(countTabulate = 3, value = "TODO(\"You must handle the error here.\")")
        }
        output.printTabulate(countTabulate = 2, value = "}")
    }

    protected open fun generateExecutionMethodBodyWithoutReturn(output: PrintWriter) {
        output.printTabulate(countTabulate = 2, value = "try{")

        output.printTabulate(
            countTabulate = 3,
            value = "service.${useCase.getMethodName()}${useCase.createParametersAsParameter()}"
        )

        output.printTabulate(countTabulate = 2, value = "}")
        output.printTabulate(countTabulate = 2, value = "catch (ex : Exception){")
        output.printTabulate(countTabulate = 3, value = "ex.printStackTrace()")
        output.printTabulate(countTabulate = 3, value = "TODO(\"You must handle the error here.\")")
        output.printTabulate(countTabulate = 2, value = "}")
    }

    protected open fun generateExecutionMethodName(output: PrintWriter) {
        output.printTabulate("suspend fun execute${useCase.createParametersSignature()} ${useCase.createReturnTypeForUseCases()}{")
    }
}