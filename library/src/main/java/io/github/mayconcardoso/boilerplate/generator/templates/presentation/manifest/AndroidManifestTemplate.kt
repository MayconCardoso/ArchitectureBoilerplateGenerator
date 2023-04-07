package io.github.mayconcardoso.boilerplate.generator.templates.presentation.manifest

import io.github.mayconcardoso.boilerplate.generator.generators.feature.context.FeatureContext
import io.github.mayconcardoso.boilerplate.generator.core.generator.blankLine
import io.github.mayconcardoso.boilerplate.generator.core.generator.printDoubleTabulate
import io.github.mayconcardoso.boilerplate.generator.core.generator.printTabulate
import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.path.ModuleFilePath
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.core.settings.featureSegment
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
open class AndroidManifestTemplate(private val moduleFilePath: ModuleFilePath) : Template() {
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