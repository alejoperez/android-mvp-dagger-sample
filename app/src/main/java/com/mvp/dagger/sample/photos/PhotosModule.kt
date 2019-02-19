package com.mvp.dagger.sample.photos

import com.mvp.dagger.sample.di.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PhotosModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PhotosViewModule::class, PhotosLogicModule::class])
    abstract fun contributePhotosFragment(): PhotosFragment

    @Module
    abstract class PhotosLogicModule {
        @Binds
        abstract fun bindPhotosPresenter(presenter: PhotosPresenter): IPhotosContract.Presenter
    }

    @Module
    abstract class PhotosViewModule {
        @Binds
        abstract fun bindPhotosView(view: PhotosFragment): IPhotosContract.View
    }

}