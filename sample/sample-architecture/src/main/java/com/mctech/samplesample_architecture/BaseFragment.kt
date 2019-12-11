package com.mctech.samplesample_architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
abstract class BaseFragment : Fragment() {
    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(getLayoutId(), container, false)
}
