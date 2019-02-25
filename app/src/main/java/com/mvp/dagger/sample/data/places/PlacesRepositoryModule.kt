package com.mvp.dagger.sample.data.places

import com.mvp.dagger.sample.data.IBaseSourceListener
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class PlacesRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPlacesRepository(repository: PlacesRepository): IPlacesDataSource

    @Singleton
    @Binds
    @Named(IBaseSourceListener.LOCAL)
    abstract fun bindPlacesLocalDataSource(localDataSource: PlacesLocalDataSource): IPlacesDataSource

    @Singleton
    @Binds
    @Named(IBaseSourceListener.REMOTE)
    abstract fun bindPlacesRemoteDataSource(remoteDataSource: PlacesRemoteDataSource): IPlacesDataSource
}