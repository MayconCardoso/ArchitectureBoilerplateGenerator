package com.mctech.architecture.generator.templates.presentation.resource

import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.templates.Template

/**
 * @author MAYCON CARDOSO on 2019-12-05.
 */
abstract class BaseLayoutTemplate(protected val moduleFilePath: ModuleFilePath) : Template() {

    override fun generate() = writeFile(this) { output ->
        output.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
        output.println("<FrameLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"")
        output.printTabulate("android:layout_width=\"match_parent\"")
        output.printTabulate("android:padding=\"24dp\"")
        output.printTabulate("android:layout_height=\"match_parent\">")

        output.blankLine()
        output.printTabulate("<TextView")
        output.printTabulate(countTabulate = 2, value = "android:layout_width=\"match_parent\"")
        output.printTabulate(countTabulate = 2, value = "android:gravity=\"center\"")
        output.printTabulate(countTabulate = 2, value = "android:textStyle=\"bold\"")
        output.printTabulate(countTabulate = 2, value = "android:textSize=\"24sp\"")
        output.printTabulate(countTabulate = 2, value = "android:text=\"If this code has helped you! Give me a star on Github.\\n\\n:)\"")
        output.printTabulate(countTabulate = 2, value = "android:layout_height=\"match_parent\"/>")

        output.blankLine()
        output.println("</FrameLayout>")
    }
}