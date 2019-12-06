package com.mctech.architecture.generator.templates.domain.interaction

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.templates.KotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
open class ResultTemplate :
    KotlinTemplate(FeatureContext.featureGenerator.domainModulePath) {
    override val folder: String
        get() = ""

    override val className: String
        get() = "Result"

    override fun generateImports(output: PrintWriter) = Unit
    override fun generateClassName(output: PrintWriter) {
        output.println("sealed class Result<out T> {")
    }

    override fun generateClassBody(output: PrintWriter) {
        output.printTabulate("data class Success<T>(val result: T) : Result<T>()")
        output.printTabulate("data class Failure(val throwable: Throwable) : Result<Nothing>()")
    }
}