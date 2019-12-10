package com.mctech.architecture.generator.templates.presentation

import com.mctech.architecture.generator.generator.printPackage
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.baseProjectPath
import com.mctech.architecture.generator.settings.featureSegment
import com.mctech.architecture.generator.templates.KotlinTemplate
import java.io.PrintWriter

abstract class PresentationKotlinTemplate(moduleFilePath: ModuleFilePath) : KotlinTemplate(moduleFilePath) {
    override val folder: String
        get() = "presentation"

    override fun getPath(): String {
        return "${baseProjectPath}features/feature-${featureSegment()}/${moduleFilePath.type.folderName}${moduleFilePath.packageValue.getSegmentedPackage()}/$className.kt"
    }

    override fun generatePackage(output: PrintWriter) {
        output.printPackage(moduleFilePath.packageValue.getPackageLine())
    }
}