package com.mvp.dagger.sample.base

import android.content.Context

interface IBaseView {

    fun isActive(): Boolean

    fun showAlert(textResource: Int)

    fun getViewContext(): Context
}