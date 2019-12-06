package com.mctech.architecture.generator.templates.presentation.manifest

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureSegment
import com.mctech.architecture.generator.templates.Template

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class AndroidManifestTemplate(private val moduleFilePath: ModuleFilePath) : Template() {
    override fun getPath(): String {
        return "features/feature-${featureSegment()}/${moduleFilePath.type.getMainFolder()}AndroidManifest.xml"
    }

    override fun generate() = writeFile(this) { output ->


        if (FeatureContext.featureGenerator.settings.presentationViewModel.hasActivity) {
            val completedFeaturePackage = moduleFilePath.packageValue.value

            output.println("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"")
            output.printTabulate("package=\"${completedFeaturePackage}\">")

            output.blankLine()
            output.printTabulate("<application>")
            output.printTabulate(
                countTabulate = 2,
                value = "<activity android:name=\".TesteActivity\" />"
            )
            output.printTabulate("</application>")

            output.blankLine()
            output.println("</manifest>")
        } else {
            output.println("<manifest package=\"com.mctech.architecture.feature.balance_teste\"/>")
        }
    }
}