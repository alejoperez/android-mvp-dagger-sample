package com.mvp.dagger.sample.main

import com.mvp.dagger.sample.data.user.IUserDataSource
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: IMainContract.View,
                                        private val userRepository: IUserDataSource): IMainContract.Presenter {

    override fun getUser() = userRepository.getUser()

    override fun logout() = userRepository.logout(view.getViewContext())

}