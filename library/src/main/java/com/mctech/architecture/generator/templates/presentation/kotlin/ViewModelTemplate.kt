package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.builder.foreachLiveData
import com.mctech.architecture.generator.builder.foreachUseCase
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.context.entityPackage
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ViewModelTemplate(modulePath: ModuleFilePath) : PresentationKotlinTemplate(modulePath) {

    override val className: String
        get() = "${featureEntityName()}ViewModel"

    override fun generateImports(output: PrintWriter) {
        output.printImport("import androidx.lifecycle.LiveData")
        output.printImport("import androidx.lifecycle.MutableLiveData")
        output.printImport("import androidx.lifecycle.viewModelScope")
        output.printImport("import kotlinx.coroutines.launch")
        output.printImport("import java.util.*")
        output.printImport("import javax.inject.Inject")
        output.printImport("${FeatureContext.featureGenerator.baseArchitecturePath.packageValue.getImportLine()}.BaseViewModel")
        output.blankLine()

        if(hasGeneratedEntity()){
            output.printImport("${entityPackage()}.${featureEntityName()}")
        }

        foreachUseCase {
            output.printImport(it.getImportPath())
        }

        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class $className @Inject constructor(${getConstructorParameters()}")
    }

    override fun generateClassBody(output: PrintWriter) {
        output.blankLine()

        foreachLiveData {
            output.printTabulate("private val _${it.name} = MutableLiveData<${it.type.getType()}>()")
            output.printTabulate("val ${it.name} : LiveData<${it.type.getType()}> = _${it.name}")
            output.blankLine()
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
        }
    }
}