package com.mvp.dagger.sample.photos.detail

import com.mvp.dagger.sample.di.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class PhotoDetailModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [PhotoDetailLogicModule::class])
    abstract fun contributePhotoDetailFragment(): PhotoDetailDialogFragment

    @Module
    abstract class PhotoDetailLogicModule {

        @Module
        companion object {
            @JvmStatic
            @FragmentScope
            @Provides
            fun providePhotoUrl(view: PhotoDetailDialogFragment): String = view.arguments?.getString(PhotoDetailDialogFragment.PHOTO_URL, "") ?: ""
        }

    }
}