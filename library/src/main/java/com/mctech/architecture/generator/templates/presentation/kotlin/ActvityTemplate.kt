package com.mctech.architecture.generator.templates.presentation.kotlin

import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printImport
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.featureEntityName
import com.mctech.architecture.generator.templates.presentation.PresentationKotlinTemplate
import java.io.PrintWriter

/**
 * @author MAYCON CARDOSO on 2019-11-27.
 */
class ActvityTemplate(modulePath: ModuleFilePath) : PresentationKotlinTemplate(modulePath) {

    override val className: String
        get() = "${featureEntityName()}Activity"

    override fun generateImports(output: PrintWriter) {
        output.printImport("import android.os.Bundle")
        output.printImport("import androidx.appcompat.app.AppCompatActivity")
        output.blankLine()
    }

    override fun generateClassName(output: PrintWriter) {
        output.println("class $className : AppCompatActivity() {")

//        import android.os.Bundle
//                import androidx.appcompat.app.AppCompatActivity
//                import com.mctech.samplesample_architecture.BaseActivity
//                import com.mctech.samplesample_architecture.ComponentState
//                import com.mctech.samplesample_architecture.extentions.bindState
//                import com.mctech.samplesample_architecture.extentions.daggerViewModel
//
//        class FeatureEmptyActivity : BaseActivity() {
//
//            private val viewModel : FindAgencyViewModel by daggerViewModel(FindAgencyViewModel::class.java)
//
//            override fun onCreate(savedInstanceState: Bundle?) {
//                super.onCreate(savedInstanceState)
//                setContentView(R.layout.activity_feature_empty)
//            }
//        }

    }

    override fun generateClassBody(output: PrintWriter) {
    }
}