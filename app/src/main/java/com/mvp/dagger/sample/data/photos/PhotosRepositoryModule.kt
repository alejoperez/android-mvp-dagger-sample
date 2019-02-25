package com.mvp.dagger.sample.data.photos

import com.mvp.dagger.sample.data.IBaseSourceListener
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class PhotosRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPhotosRepository(repository: PhotosRepository): IPhotosDataSource

    @Singleton
    @Binds
    @Named(IBaseSourceListener.LOCAL)
    abstract fun bindPhotosLocalDataSource(localDataSource: PhotosLocalDataSource): IPhotosDataSource

    @Singleton
    @Binds
    @Named(IBaseSourceListener.REMOTE)
    abstract fun bindPhotosRemoteDataSource(remoteDataSource: PhotosRemoteDataSource): IPhotosDataSource
}