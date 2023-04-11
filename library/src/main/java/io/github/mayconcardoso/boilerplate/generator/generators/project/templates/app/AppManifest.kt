package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.app

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFileString
import io.github.mayconcardoso.boilerplate.generator.core.settings.GlobalSettings
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

/**
 * Creates the design module manifest.
 */
class AppManifest : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/app/src/main/AndroidManifest.xml"
  }

  override fun generate() = writeFileString(this) {
    """
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="${GlobalSettings.projectSettings.projectPackage.value}">

    <application
        android:name=".ProjectApplication"
        android:icon="@android:drawable/ic_menu_week"
        android:label="@string/app_name"
        android:roundIcon="@android:drawable/ic_menu_week"
        android:supportsRtl="true"
        android:theme="@style/Theme.Material3.DayNight"
        tools:targetApi="31">

        <activity
            android:name=".HomeActivity"
            android:exported="true">

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

        </activity>
    </application>

</manifest>
    """.trimIndent()
  }

}