package io.github.mayconcardoso.boilerplate.generator.generators.project.templates.app

import io.github.mayconcardoso.boilerplate.generator.core.generator.writeFileString
import io.github.mayconcardoso.boilerplate.generator.core.settings.baseProjectPath
import io.github.mayconcardoso.boilerplate.generator.templates.Template

class AppHomeActivityLayoutFile : Template() {

  override fun getPath(): String {
    return "$baseProjectPath/app/src/main/res/layout/activity_home.xml"
  }

  override fun generate() = writeFileString(this) {
    """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="@string/app_name"
        android:textSize="38sp"
        android:textStyle="bold" />

</androidx.constraintlayout.widget.ConstraintLayout>
    """.trimIndent()
  }

}