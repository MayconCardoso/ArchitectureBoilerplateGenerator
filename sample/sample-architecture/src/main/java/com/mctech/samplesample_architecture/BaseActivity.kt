package com.mctech.samplesample_architecture

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
abstract class BaseActivity : AppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        // Bug of images on previous platform version.
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        // Dagger injection delegate.
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}