package com.mvp.dagger.sample.splash

import com.mvp.dagger.sample.base.IBaseView

interface ISplashContract {

    interface View: IBaseView {
        fun goToNextScreen()
    }

    interface Presenter {
        fun isLoggedIn(): Boolean
    }

}