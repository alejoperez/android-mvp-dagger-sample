package com.mvp.dagger.sample.places

import com.mvp.dagger.sample.di.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlacesModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PlacesViewModule::class, PlacesLogicModule::class])
    abstract fun contributePlacesFragment(): PlacesFragment

    @Module
    abstract class PlacesLogicModule {
        @Binds
        abstract fun bindPlacesPresenter(presenter: PlacesPresenter): IPlacesContract.Presenter
    }

    @Module
    abstract class PlacesViewModule {
        @Binds
        abstract fun bindPlacesView(view: PlacesFragment): IPlacesContract.View
    }

}