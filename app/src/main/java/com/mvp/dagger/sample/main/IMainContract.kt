package com.mvp.dagger.sample.main

import com.mvp.dagger.sample.base.IBaseView
import com.mvp.dagger.sample.data.User

interface IMainContract {

    interface View: IBaseView

    interface Presenter {
        fun getUser(): User?
        fun logout()
    }

}