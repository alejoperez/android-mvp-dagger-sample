package com.mvp.dagger.sample.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    fun setToolbarTitle(textInt: Int) = toolbar?.setTitle(textInt)

    override fun isActive(): Boolean = !isFinishing

    override fun showAlert(textResource: Int) {
        if (isActive()) {
            alert(textResource) {
                yesButton { dialog -> dialog.dismiss() }
            }.show()
        }
    }

    override fun getViewContext(): Context = this


    fun replaceFragment(fragment: Fragment, @IdRes fragmentId: Int, tag: String) {
        try {
            if (isNewFragment(fragmentId, tag)) {
                supportFragmentManager.beginTransaction().replace(fragmentId, fragment, tag).commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun isNewFragment(@IdRes fragmentId: Int, tag: String): Boolean = !getCurrentFragment(fragmentId)?.tag.equals(tag)

    private fun getCurrentFragment(@IdRes fragmentId: Int): Fragment? = supportFragmentManager.findFragmentById(fragmentId)
}