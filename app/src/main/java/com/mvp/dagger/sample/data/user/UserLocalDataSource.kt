package com.mvp.dagger.sample.data.user

import android.content.Context
import com.mvp.dagger.sample.data.User
import com.mvp.dagger.sample.storage.PreferenceManager
import com.mvp.dagger.sample.webservice.LoginRequest
import com.mvp.dagger.sample.webservice.RegisterRequest
import io.realm.Realm
import javax.inject.Inject

class UserLocalDataSource @Inject constructor() : IUserDataSource {

    override fun isLoggedIn(context: Context): Boolean = PreferenceManager<String>(context).findPreference(PreferenceManager.ACCESS_TOKEN,"").isNotEmpty()

    override fun getUser(): User? = Realm.getDefaultInstance().where(User::class.java).findFirst()

    override fun saveUser(user: User) {
        Realm.getDefaultInstance().executeTransactionAsync{
            realm -> realm.insertOrUpdate(user)
        }
    }

    override fun logout(context: Context) = PreferenceManager<String>(context).putPreference(PreferenceManager.ACCESS_TOKEN,"")

    override fun login(context: Context, request: LoginRequest, listener: UserRepository.ILoginListener) = throw UnsupportedOperationException()

    override fun register(context: Context, request: RegisterRequest, listener: UserRepository.IRegisterListener) = throw UnsupportedOperationException()

}