package com.mvp.dagger.sample.di

import com.mvp.dagger.sample.data.photos.PhotosRepositoryModule
import com.mvp.dagger.sample.data.places.PlacesRepositoryModule
import com.mvp.dagger.sample.data.user.UserRepositoryModule
import dagger.Module

@Module(
        includes = [
            UserRepositoryModule::class,
            PlacesRepositoryModule::class,
            PhotosRepositoryModule::class
        ]
)
abstract class RepositoriesModule