package com.mctech.architecture.generator.templates.presentation.manifest

import com.mctech.architecture.generator.generator.FileGenerator
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.FilePath
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featurePackage
import com.mctech.architecture.generator.settings.featureSegment

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
class AndroidManifestTemplate(private val moduleFilePath: ModuleFilePath) : FilePath, FileGenerator {
    override fun getPath(): String {
        return "features/feature-${featureSegment()}/${moduleFilePath.type.getMainFolder()}AndroidManifest.xml"
    }

    override fun generate() = writeFile(this){ output ->
        output.println("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"")
        output.printTabulate("package=\"${featurePackage()}\">")

        output.printTabulate("<application>")
        output.printTabulate(countTabulate = 2, value = "<activity android:name=\".TesteActivity\" />")
        output.printTabulate("</application>")

        output.println("</manifest>")

    }
}