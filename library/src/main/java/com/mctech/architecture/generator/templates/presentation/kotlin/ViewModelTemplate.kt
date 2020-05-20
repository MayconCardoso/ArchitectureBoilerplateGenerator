package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.builder.*
import com.mctech.architecture.generator.class_contract.Type
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.generator.*
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
open class ViewModelTemplate(modulePath: ModuleFilePath) : PresentationKotlinTemplate(modulePath) {

    override val className: String
        get() = "${featureEntityName()}ViewModel"

    override fun generateImports(output: PrintWriter) {
        val baseArchitecturePackage = FeatureContext.featureGenerator.baseArchitecturePath.packageValue.getImportLine()

        output.printImport("import androidx.lifecycle.LiveData")
        output.printImport("import androidx.lifecycle.MutableLiveData")
        output.printImport("import androidx.lifecycle.viewModelScope")
        output.printImport("import kotlinx.coroutines.launch")
        output.printImport("import java.util.*")
        output.printImport("import javax.inject.Inject")
        output.printImport("$baseArchitecturePackage.BaseViewModel")
        output.printImport("$baseArchitecturePackage.ComponentState")
        output.printImport("$baseArchitecturePackage.ktx.*")
        output.printImport("import ${FeatureContext.featureGenerator.domainModulePath.packageValue.value}.Result")
        output.blankLine()

        if(hasGeneratedEntity()){
            output.printImport("${entityPackage()}.${featureEntityName()}")
        }

        foreachUseCase {
            output.printImport(it.getImportPath())
        }

        if(FeatureContext.featureGenerator.listOfUserInteraction.isNotEmpty()){
            output.printImport("import com.mctech.architecture.mvvm.x.core.OnInteraction")
        }

        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class $className (${getConstructorParameters()}")
    }

    override fun generateClassBody(output: PrintWriter) {
        output.blankLine()

        // Livedata
        foreachLiveData {
            output.printTabulate("private val _${it.name} = MutableLiveData<${it.type.getType()}>()")
            output.printTabulate("val ${it.name} : LiveData<${it.type.getType()}> = _${it.name}")
            output.blankLine()
        }

        // Components
        foreachComponentState {
            output.printTabulate("private val _${it.name} : MutableLiveData<ComponentState<${it.type.getType()}>> = MutableLiveData(ComponentState.Initializing)")
            output.printTabulate("val ${it.name} : LiveData<ComponentState<${it.type.getType()}>> = _${it.name}")
            output.blankLine()
        }

        // Components
        foreachUserInteraction {
            val interactionName = "${featureEntityName()}UserInteraction.${it.name}"
            output.printTabulate("@OnInteraction($interactionName::class)")
            if(it.parameters.isEmpty()){
                output.printTabulate("private suspend fun ${it.getMethodName()}(){")
            }
            else{
                output.printTabulate("private suspend fun ${it.getMethodName()}(interaction : $interactionName){")
            }
            printInteractionBody(output, it)
            output.blankLine()
            output.printTabulate("}")
            output.blankLine()
        }

    }

    private fun printInteractionBody(output: PrintWriter, it: UserInteractionBuilder) {
        if(it.connectedState != null){
            if(it.connectedState.type is Type.ListOfGeneratedEntity || it.connectedState.type is Type.ListOfGeneratedEntity){
                output.printDoubleTabulate("_${it.connectedState.name}.changeToListLoadingState()")
            }
            else{
                output.printDoubleTabulate("_${it.connectedState.name}.changeToLoadingState()")
            }

            output.blankLine()
        }

        if(it.connectedUseCase != null){
            if(it.connectedUseCase.returnType is Type.ResultOf){
                output.printDoubleTabulate("when(val result = ${it.connectedUseCase.getMethodName()}Case.execute(${bindParameters(it)})){")
                output.printTripleTabulate("is Result.Success -> {")
                if(it.connectedState != null){
                    output.printTabulate(
                        countTabulate = 4,
                        value = "_${it.connectedState.name}.changeToSuccessState(result.result)"
                    )
                }
                output.printTripleTabulate("}")
                output.printTripleTabulate("is Result.Failure -> {")
                if(it.connectedState != null){
                    output.printTabulate(
                        countTabulate = 4,
                        value = "_${it.connectedState.name}.changeToErrorState(result.throwable)"
                    )
                }
                output.printTripleTabulate("}")
                output.printDoubleTabulate("}")
            }
        }
    }

    private fun bindParameters(it: UserInteractionBuilder): String {
        if(it.connectedUseCase != null && it.connectedUseCase.parameters.isNotEmpty()){
            // Create variable name foreach use case
            return it.connectedUseCase.parameters
                .map {
                    "interaction.${it.name}, "
                }
                // Reduce it to an string.
                .reduce {
                        acc, useCaseVariable ->  acc + useCaseVariable
                }
                // Remove last comma
                .removeSuffix(", ")
        }
        else {
            return ""
        }
    }

    private fun getConstructorParameters(): String {
        if(FeatureContext.featureGenerator.listOfUseCases.isEmpty()){
            return ") : BaseViewModel() {"
        }

        return FeatureContext.featureGenerator.listOfUseCases
            // Create variable name foreach use case
            .map {
                "\n\tprivate val ${it.getMethodName()}Case: ${it.name},"
            }
            // Reduce it to an string.
            .reduce {
                    acc, useCaseVariable ->  acc + useCaseVariable
            }
            // Remove last comma
            .removeSuffix(",")
            // Finish constructor method.
            .plus("\n) : BaseViewModel() {")
    }

    private fun hasGeneratedEntity() : Boolean{
        return FeatureContext.featureGenerator.listOfLiveData.any {
            it.hasGeneratedEntity()
        } ||  FeatureContext.featureGenerator.listOfComponentState.any {
            it.hasGeneratedEntity()
        }
    }
}