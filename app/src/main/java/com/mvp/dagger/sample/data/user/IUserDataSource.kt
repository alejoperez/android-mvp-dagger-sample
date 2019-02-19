package com.mvp.dagger.sample.data.user

import android.content.Context
import com.mvp.dagger.sample.data.User
import com.mvp.dagger.sample.webservice.LoginRequest
import com.mvp.dagger.sample.webservice.RegisterRequest

interface IUserDataSource {
    fun getUser(): User?
    fun saveUser(user: User)
    fun login(context: Context, request: LoginRequest, listener: UserRepository.ILoginListener)
    fun register(context: Context, request: RegisterRequest, listener: UserRepository.IRegisterListener)
    fun isLoggedIn(context: Context): Boolean
    fun logout(context: Context)
}