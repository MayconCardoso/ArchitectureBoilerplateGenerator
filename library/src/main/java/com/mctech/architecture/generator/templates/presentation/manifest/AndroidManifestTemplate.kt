package com.mctech.architecture.generator.templates.presentation.manifest

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printDoubleTabulate
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.baseProjectPath
import com.mctech.architecture.generator.settings.featureSegment
import com.mctech.architecture.generator.templates.Template

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class AndroidManifestTemplate(private val moduleFilePath: ModuleFilePath) : Template() {
    override fun getPath(): String {
        return "${baseProjectPath}features/feature-${featureSegment()}/${moduleFilePath.type.getMainFolder()}AndroidManifest.xml"
    }

    override fun generate() = writeFile(this) { output ->

        val completedFeaturePackage = moduleFilePath.packageValue.value

        if (FeatureContext.featureGenerator.settings.presentationViewModel.hasActivity) {
            output.println("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"")
            output.printTabulate("package=\"$completedFeaturePackage\">")

            output.blankLine()
            output.printTabulate("<application>")
            output.printDoubleTabulate("<activity android:name=\".${FeatureContext.featureGenerator.presentationActivity.className}\" />")
            output.printTabulate("</application>")

            output.blankLine()
            output.println("</manifest>")
        } else {
            output.println("<manifest package=\"$completedFeaturePackage\"/>")
        }
    }
}