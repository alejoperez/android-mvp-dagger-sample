package com.mvp.dagger.sample.data.user

import android.content.Context
import com.mvp.dagger.sample.data.IBaseSourceListener
import com.mvp.dagger.sample.data.User
import com.mvp.dagger.sample.storage.PreferenceManager
import com.mvp.dagger.sample.webservice.LoginRequest
import com.mvp.dagger.sample.webservice.LoginResponse
import com.mvp.dagger.sample.webservice.RegisterRequest
import com.mvp.dagger.sample.webservice.RegisterResponse
import javax.inject.Inject
import javax.inject.Named

class UserRepository
@Inject constructor(@Named(IBaseSourceListener.LOCAL) private val localDataSource: IUserDataSource,
                    @Named(IBaseSourceListener.REMOTE) private val remoteDataSource: IUserDataSource) : IUserDataSource {

    override fun saveUser(user: User) = localDataSource.saveUser(user)

    override fun getUser(): User? = localDataSource.getUser()

    override fun login(context: Context, request: LoginRequest, listener: ILoginListener) {
        remoteDataSource.login(context, request, object : ILoginListener {
            override fun onLoginSuccess(response: LoginResponse?) {
                response?.let {
                    PreferenceManager<String>(context).putPreference(PreferenceManager.ACCESS_TOKEN,response.accessToken)
                    localDataSource.saveUser(response.toUser())
                    listener.onLoginSuccess(response)
                }
            }

            override fun onLoginFailure() = listener.onLoginFailure()

            override fun onNetworkError() = listener.onNetworkError()
        })
    }

    override fun register(context: Context, request: RegisterRequest, listener: IRegisterListener) {
        remoteDataSource.register(context, request, object : IRegisterListener {
            override fun onRegisterSuccess(response: RegisterResponse?) {
                response?.let {
                    PreferenceManager<String>(context).putPreference(PreferenceManager.ACCESS_TOKEN, response.accessToken)
                    localDataSource.saveUser(response.toUser())
                    listener.onRegisterSuccess(response)
                }
            }

            override fun onRegisterFailure() = listener.onRegisterFailure()

            override fun onNetworkError() = listener.onNetworkError()
        })
    }

    override fun isLoggedIn(context: Context): Boolean = localDataSource.isLoggedIn(context)

    override fun logout(context: Context) = localDataSource.logout(context)

    interface IRegisterListener : IBaseSourceListener {
        fun onRegisterSuccess(response: RegisterResponse?)
        fun onRegisterFailure()
    }

    interface ILoginListener : IBaseSourceListener {
        fun onLoginSuccess(response: LoginResponse?)
        fun onLoginFailure()
    }

}
