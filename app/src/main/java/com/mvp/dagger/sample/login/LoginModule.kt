package com.mvp.dagger.sample.login

import com.mvp.dagger.sample.di.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginViewModule::class, LoginLogicModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @Module
    abstract class LoginLogicModule {
        @Binds
        abstract fun bindLoginPresenter(presenter: LoginPresenter): ILoginContract.Presenter
    }

    @Module
    abstract class LoginViewModule {
        @Binds
        abstract fun bindLoginView(view: LoginActivity): ILoginContract.View
    }

}