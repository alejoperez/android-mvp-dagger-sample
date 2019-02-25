package com.mvp.dagger.sample.data.user

import com.mvp.dagger.sample.data.IBaseSourceListener
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class UserRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: UserRepository): IUserDataSource

    @Singleton
    @Binds
    @Named(IBaseSourceListener.LOCAL)
    abstract fun bindUserLocalDataSource(localDataSource: UserLocalDataSource): IUserDataSource

    @Singleton
    @Binds
    @Named(IBaseSourceListener.REMOTE)
    abstract fun bindUserRemoteDataSource(remoteDataSource: UserRemoteDataSource): IUserDataSource

}