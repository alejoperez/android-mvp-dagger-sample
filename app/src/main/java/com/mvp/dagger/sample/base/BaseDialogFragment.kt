package com.mvp.dagger.sample.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

abstract class BaseDialogFragment: DialogFragment(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    private lateinit var fragmentContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    override fun isActive(): Boolean = isAdded

    override fun showAlert(textResource: Int) {
        activity?.let {
            if (isActive() && !it.isFinishing) {
                it.alert(textResource) {
                    yesButton { dialog -> dialog.dismiss() }
                }.show()
            }
        }
    }

    override fun getViewContext(): Context = fragmentContext
}