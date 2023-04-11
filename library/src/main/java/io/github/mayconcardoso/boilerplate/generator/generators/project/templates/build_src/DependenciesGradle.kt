package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.build_src

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFile
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.generators.project.context.AndroidTargets
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the design module compose gradle project.
 */
class DependenciesGradle: Template() {

  override fun getPath(): String {
    return "$baseProjectPath/buildSrc/dependencies.gradle"
  }

  override fun generate() = writeFile(this) { outputWriter ->
    outputWriter.println(
      """
      ext.libraries = [
              //==========================================================================================
              // Platform - Kotlin
              //==========================================================================================
              "kotlinJdk": "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10",
              "kotlinReflection": "org.jetbrains.kotlin:kotlin-reflect:1.7.10",
              "kotlinCoroutineCore": "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4",
              "kotlinCoroutineTest": "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4",
              "kotlinCoroutineAndroid": "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4",
      
              //==========================================================================================
              // Application - Google
              //==========================================================================================
              "googleDagger": "com.google.dagger:dagger:2.45",
              "googleHiltAndroid": "com.google.dagger:hilt-android:2.45",
              "googleHiltCompiler": "com.google.dagger:hilt-compiler:2.45",
              "googleMaterialDesign": "com.google.android.material:material:1.8.0",
      
              //==========================================================================================
              // Personal library
              //==========================================================================================
              "mvvmCore": "io.github.mayconcardoso:mvvm-core:2.1.0",
              "mvvmCoreKtx": "io.github.mayconcardoso:mvvm-core-ktx:2.1.0",
              "mvvmCoreTesting": "io.github.mayconcardoso:mvvm-core-testing:2.1.0",
      
              //==========================================================================================
              // Application - Room
              //==========================================================================================
              "androidRoomKtx": "androidx.room:room-ktx:2.5.0",
              "androidRoomRuntime": "androidx.room:room-compiler:2.5.0",
              "androidRoomTesting": "androidx.room:room-testing:2.5.0",
              "androidRoomCompiler": "androidx.room:room-runtime:2.5.0",
      
              //==========================================================================================
              // Application - AndroidX
              //==========================================================================================
              "androidCoreKtx": "androidx.core:core-ktx:1.9.0",
              "androidCardView": "androidx.cardview:cardview:1.0.0",
              "androidAppCompat": "androidx.appcompat:appcompat:1.6.1",
              "androidViewPager2": "androidx.viewpager2:viewpager2:1.0.0",
              "androidFragmentKtx": "androidx.fragment:fragment-ktx:1.5.5",
              "androidViewBinding": "androidx.databinding:viewbinding:7.4.2",
              "androidRecyclerView": "androidx.recyclerview:recyclerview:1.3.0",
              "androidNavigationUiKtx": "androidx.navigation:navigation-ui-ktx:2.5.3",
              "androidConstraintLayout": "androidx.constraintlayout:constraintlayout:2.1.4",
              "androidLifecycleRuntimeKtx": "androidx.lifecycle:lifecycle-runtime-ktx:2.6.0",
              "androidLifecycleLiveDataKtx": "androidx.lifecycle:lifecycle-livedata-ktx:2.6.0",
              "androidLifecycleViewModelKtx": "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0",
              "androidNavigationFragmentKtx": "androidx.navigation:navigation-fragment-ktx:2.5.3",
      
              //==========================================================================================
              // Application - Compose
              //==========================================================================================
              "androidComposeUi": "androidx.compose.ui:ui:1.3.3",
              "androidComposeHilt": "androidx.hilt:hilt-navigation-compose:1.0.0",
              "androidComposeMaterial": "androidx.compose.material:material:1.3.1",
              "androidComposeTooling": "androidx.compose.ui:ui-tooling:1.3.3",
              "androidComposeToolingPreview": "androidx.compose.ui:ui-tooling-preview:1.3.3",
              "androidComposeRuntimeLivedata": "androidx.compose.runtime:runtime-livedata:1.3.3",
              "androidComposeViewModel": "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0",
              "androidComposeNavigation": "androidx.navigation:navigation-compose:2.5.3",
      
              //==========================================================================================
              // Application - Testing
              //==========================================================================================
              "androidTestingMockk": "io.mockk:mockk:1.12.8",
              "androidTestingJunit": "junit:junit:4.13.2",
              "androidTestingRules": "androidx.test:rules:1.4.0",
              "androidTestingRunner": "androidx.test:runner:1.4.0",
              "androidTestingAssertj": "org.assertj:assertj-core:3.23.1",
              "androidTestingMonitor": "androidx.test:monitor:1.6.0",
              "androidTestingArchCore": "androidx.arch.core:core-testing:2.1.0",
              "androidTestingExtJunit": "androidx.test.ext:junit:1.1.3",
      ]
      """.trimIndent()
    )
  }
}