package com.mctech.architecture.generator.templates.data.datasource

import com.mctech.architecture.generator.context.retrofitAPIPackage
import com.mctech.architecture.generator.context.retrofitApiFeatureName
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-28.
 */
class RemoteDataSourceTemplate(modulePath: ModuleFilePath) :
    BaseDataSourceImplementationTemplate(modulePath) {

    override val className: String
        get() = "Remote${featureEntityName()}DataSource"

    override fun createClassParameters(): String = "(private val api : ${featureEntityName()}API)"

    override fun generateImports(output: PrintWriter) {
        output.printImport("${retrofitAPIPackage()}.${retrofitApiFeatureName()}")
        super.generateImports(output)
    }
}