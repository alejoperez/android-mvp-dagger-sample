package com.mvp.dagger.sample.main

import com.mvp.dagger.sample.di.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainViewModule::class, MainLogicModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @Module
    abstract class MainLogicModule {
        @Binds
        abstract fun bindMainPresenter(presenter: MainPresenter): IMainContract.Presenter
    }

    @Module
    abstract class MainViewModule {
        @Binds
        abstract fun bindMainView(view: MainActivity): IMainContract.View
    }

}