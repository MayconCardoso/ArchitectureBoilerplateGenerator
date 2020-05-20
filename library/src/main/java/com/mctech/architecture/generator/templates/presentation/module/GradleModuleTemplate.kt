package com.mctech.architecture.generator.templates.presentation.module

import com.mctech.architecture.generator.context.FeatureContext
import com.mctech.architecture.generator.generator.blankLine
import com.mctech.architecture.generator.generator.printTabulate
import com.mctech.architecture.generator.generator.writeFile
import com.mctech.architecture.generator.path.ModuleFilePath
import com.mctech.architecture.generator.settings.baseProjectPath
import com.mctech.architecture.generator.settings.featureSegment
import com.mctech.architecture.generator.templates.Template

/**
 * @author MAYCON CARDOSO on 2019-12-02.
 */
open class GradleModuleTemplate(private val moduleFilePath: ModuleFilePath) : Template() {
    override fun getPath(): String {
        return "$baseProjectPath${moduleFilePath.type.getSourceFolder()}features/feature-${featureSegment()}/build.gradle"
    }

    override fun generate() = writeFile(this){ output ->
        output.println("apply plugin: 'com.android.library'")
        output.println("apply plugin: 'kotlin-android'")
        output.println("apply plugin: 'kotlin-android-extensions'")
        output.blankLine()

        output.println("android {")
        output.printTabulate("compileSdkVersion 29")
        output.printTabulate("buildToolsVersion \"29.0.0\"")

        output.blankLine()
        output.printTabulate("defaultConfig {")
        output.printTabulate( countTabulate = 2, value = "minSdkVersion 21")
        output.printTabulate( countTabulate = 2, value = "targetSdkVersion 29")
        output.printTabulate( "}")

        output.blankLine()
        output.printTabulate("buildTypes {")
        output.printTabulate(countTabulate = 2, value = "release {")
        output.blankLine()
        output.printTabulate(countTabulate = 2, value = "}")
        output.printTabulate("}")
        output.println("}")

        val architectureModuleName  = FeatureContext.featureGenerator.baseArchitecturePath.gradleModuleName
        val domainModule            = FeatureContext.featureGenerator.domainModulePath.gradleModuleName

        output.blankLine()
        output.println("dependencies {")
        output.printTabulate("implementation project(path: \"$domainModule\")")
        output.blankLine()

        output.printTabulate("implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61'")
        output.blankLine()

        output.printTabulate("implementation 'androidx.appcompat:appcompat:1.1.0'")
        output.printTabulate("implementation 'androidx.core:core-ktx:1.1.0'")
        output.blankLine()

        output.printTabulate("implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-rc03'")
        output.printTabulate("implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0-rc03'")
        output.printTabulate("implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-rc03'")
        output.printTabulate("implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0-rc03'")
        output.blankLine()
        output.printTabulate("// Personal libraries")
        output.printTabulate("implementation 'com.mctech.library.keyboard:visibilitymonitor:1.+'")
        output.printTabulate("implementation 'com.mctech.library.keyboard:visibilitymonitor-ktx:1.+'")
        output.printTabulate("implementation 'com.mctech.architecture.mvvm:x-core:1.+'")
        output.printTabulate("implementation 'com.mctech.architecture.mvvm:x-core-ktx:1.+'")
        output.printTabulate("implementation 'com.mctech.architecture.mvvm:x-view-ktx:1.+'")
        output.printTabulate("implementation 'com.mctech.architecture.mvvm:x-core-databinding:1.+'")
        output.println("}")
    }
}