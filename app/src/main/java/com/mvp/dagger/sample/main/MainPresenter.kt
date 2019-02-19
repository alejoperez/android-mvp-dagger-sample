package com.mvp.dagger.sample.main

import com.mvp.dagger.sample.data.user.UserRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: IMainContract.View): IMainContract.Presenter {

    override fun getUser() = UserRepository.getInstance().getUser()

    override fun logout() = UserRepository.getInstance().logout(view.getViewContext())

}