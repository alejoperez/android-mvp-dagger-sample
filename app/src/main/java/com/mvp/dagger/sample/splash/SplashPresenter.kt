package com.mvp.dagger.sample.splash

import com.mvp.dagger.sample.data.user.UserRepository
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val view: ISplashContract.View): ISplashContract.Presenter {

    override fun isLoggedIn(): Boolean = UserRepository.getInstance().isLoggedIn(view.getViewContext())

}