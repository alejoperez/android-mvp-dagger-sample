package com.mvp.dagger.sample.register

import com.mvp.dagger.sample.di.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RegisterModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [RegisterViewModule::class, RegisterLogicModule::class])
    abstract fun contributeRegisterActivity(): RegisterActivity

    @Module
    abstract class RegisterLogicModule {
        @Binds
        abstract fun bindRegisterPresenter(presenter: RegisterPresenter): IRegisterContract.IRegisterPresenter
    }

    @Module
    abstract class RegisterViewModule {
        @Binds
        abstract fun bindRegisterView(view: RegisterActivity): IRegisterContract.IRegisterView
    }

}