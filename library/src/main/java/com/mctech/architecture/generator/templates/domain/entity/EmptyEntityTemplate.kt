package com.mctech.architecture.generator.templates.domain.entity

import com.mctech.architecture.generator.class_contract.customTypeImport
import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
open class EmptyEntityTemplate(modulePath: ModuleFilePath) : KotlinTemplate(modulePath, false) {

    override val folder: String
        get() = "entity"

    override val className: String
        get() = featureEntityName()

    private val fields = FeatureContext.featureGenerator.listOfFieldsOnEntity

    override fun generateImports(output: PrintWriter) {
        customTypeImport(output, fields)
        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        // There is no field.
        if(FeatureContext.featureGenerator.listOfFieldsOnEntity.isEmpty()){
            output.println("class $className")
            return
        }

        // Open lass..
        output.println("data class $className(")

        // Print fields.
        output.println(
            FeatureContext.featureGenerator.listOfFieldsOnEntity
                .map {
                    "\tval ${it.name} : ${it.type.getType()},\n"
                }
                .reduce {
                        acc, useCaseVariable ->  acc + useCaseVariable
                }
                .removeSuffix(",\n")
        )

        // Close class
        output.println(")")
    }

    override fun generateClassBody(output: PrintWriter) = Unit
}