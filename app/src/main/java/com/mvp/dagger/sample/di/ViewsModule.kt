package com.mvp.dagger.sample.di

import com.mvp.dagger.sample.login.LoginModule
import com.mvp.dagger.sample.main.MainModule
import com.mvp.dagger.sample.photos.PhotosModule
import com.mvp.dagger.sample.photos.detail.PhotoDetailModule
import com.mvp.dagger.sample.places.PlacesModule
import com.mvp.dagger.sample.register.RegisterModule
import com.mvp.dagger.sample.splash.SplashModule
import dagger.Module

@Module(
        includes = [
            SplashModule::class,
            RegisterModule::class,
            LoginModule::class,
            MainModule::class,
            PlacesModule::class,
            PhotosModule::class,
            PhotoDetailModule::class
        ]
)
abstract class ViewsModule