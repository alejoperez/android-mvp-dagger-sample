package com.mvp.dagger.sample.splash

import com.mvp.dagger.sample.di.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class SplashModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashViewModule::class, SplashLogicModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @Module
    abstract class SplashLogicModule {
        @Binds
        abstract fun bindSplashPresenter(presenter: SplashPresenter): ISplashContract.Presenter
    }

    @Module
    abstract class SplashViewModule {
        @Binds
        abstract fun bindSplashView(view: SplashActivity): ISplashContract.View
    }
}