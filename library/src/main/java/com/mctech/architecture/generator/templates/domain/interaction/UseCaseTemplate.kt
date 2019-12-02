package com.mctech.architecture.generator.templates.domain.interaction

import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.class_contract.customTypeImport
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.context.serviceFeatureName
import com.mctech.architecture.generator.context.servicePackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.generator.usecase.UseCase
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.Template
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
open class UseCaseTemplate(val useCase: UseCase, modulePath: ModuleFilePath) :
    Template(modulePath) {
    override val folder: String
        get() = "interaction"

    override val className: String
        get() = useCase.name

    override fun generate(output: PrintWriter) {
        // Print custom types
        customTypeImport(output, useCase.parameters)

        if (useCase.isDaggerInjectable) {
            output.printImport("import javax.inject.Inject")
        }

        // There is an interaction result as a type or a parameter.
        if(hasInteractionResultEntity()){
            // Create the result template
            ResultTemplate().generate()

            output.printImport(FeatureContext.featureGenerator.domainModulePath.packageValue.getImportLine() + ".Result")
        }

        // There is a generated entity as a type return or a parameter.
        if (hasGeneratedEntity()) {
            output.printImport("${entityPackage()}.${featureEntityName()}")
        }

        output.printImport("${servicePackage()}.$serviceFeatureName")
        output.blankLine()

        // Print class declaration
        generateClassName(output)

        // Print execute method declaration
        generateExecutionMethodName(output)

        // Print execute method body
        generateExecutionMethodBody(output)

        // Print end of method body
        output.printTabulate("}")

        // Print end of the class
        output.println("}")
    }

    private fun generateExecutionMethodBody(output: PrintWriter) {
        if (useCase.returnType is Type.Unit) {
            generateExecutionMethodBodyWithoutReturn(output)
        }
        else{
            generateExecutionMethodBodyWithReturn(output)
        }
    }

    protected open fun generateExecutionMethodBodyWithReturn(output: PrintWriter) {
        output.printTabulate(countTabulate = 2, value = "try{")

        output.printTabulate(
            countTabulate = 3,
            value = "return Result.Success(service.${getServiceMethodName()}${createParametersAsParameter()})"
        )

        output.printTabulate(countTabulate = 2, value = "}")
        output.printTabulate(countTabulate = 2, value = "catch (ex : Exception){")
        output.printTabulate(countTabulate = 3, value = "ex.printStackTrace()")
        output.printTabulate(countTabulate = 3, value = "return Result.Failure(ex)")
        output.printTabulate(countTabulate = 2, value = "}")
    }

    protected open fun generateExecutionMethodBodyWithoutReturn(output: PrintWriter) {
        output.printTabulate(countTabulate = 2, value = "try{")

        output.printTabulate(
            countTabulate = 3,
            value = "service.${getServiceMethodName()}${createParametersAsParameter()}"
        )

        output.printTabulate(countTabulate = 2, value = "}")
        output.printTabulate(countTabulate = 2, value = "catch (ex : Exception){")
        output.printTabulate(countTabulate = 3, value = "ex.printStackTrace()")
        output.printTabulate(countTabulate = 2, value = "}")
    }

    protected open fun generateExecutionMethodName(output: PrintWriter) {
        output.printTabulate("suspend fun execute${createParametersSignature()} ${createReturnType()}{")
    }

    protected open fun generateClassName(output: PrintWriter) {
        output.println("class ${className}${createDaggerInjectable()}(private val service : ${serviceFeatureName}){")
    }

    protected open fun createReturnType(): String {
        if (useCase.returnType is Type.Unit) {
            return ""
        }
        return ": " + useCase.returnType.getType()
    }

    protected open fun createParametersSignature(): String {
        if (useCase.parameters.isEmpty()) {
            return "()"
        }

        var parameters = "("
        useCase.parameters.forEach {
            parameters = parameters.plus("${it.name}: ${it.type.getType()}, ")
        }
        parameters = parameters.removeSuffix(", ").plus(")")
        return parameters
    }

    protected open fun createParametersAsParameter(): String {
        if (useCase.parameters.isEmpty()) {
            return "()"
        }

        var parameters = "("
        useCase.parameters.forEach {
            parameters = parameters.plus("${it.name}, ")
        }
        parameters = parameters.removeSuffix(", ").plus(")")
        return parameters
    }

    private fun createDaggerInjectable(): String {
        if (useCase.isDaggerInjectable) {
            return " @Inject constructor"
        }

        return ""
    }

    private fun hasGeneratedEntity(): Boolean {
        return useCase.returnType is Type.GeneratedEntity
                || useCase.parameters.any { it.type is Type.GeneratedEntity }
                || useCase.parameters.any { if (it.type is Type.ResultOf) it.type.type is Type.GeneratedEntity else false }
    }

    private fun hasInteractionResultEntity(): Boolean {
        return useCase.returnType is Type.ResultOf
                || useCase.parameters.any { it.type is Type.ResultOf }
    }

    private fun getServiceMethodName() : String{
        val methodName = useCase.name.removeSuffix("Case")
        return methodName
    }
}