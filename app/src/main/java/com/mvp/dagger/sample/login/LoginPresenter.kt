package com.mvp.dagger.sample.login

import com.mvp.dagger.sample.R
import com.mvp.dagger.sample.data.user.UserRepository
import com.mvp.dagger.sample.webservice.LoginRequest
import com.mvp.dagger.sample.webservice.LoginResponse
import javax.inject.Inject


class LoginPresenter @Inject constructor(private val view: ILoginContract.View): ILoginContract.Presenter, UserRepository.ILoginListener {

    override fun isValidEmail(email: String): Boolean = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun isValidPassword(password: String): Boolean = password.isNotEmpty()

    override fun isValidForm(email: String, password: String): Boolean = isValidEmail(email) && isValidPassword(password)

    override fun login(email: String, password: String) {
        view.showProgress()
        UserRepository.getInstance().login(view.getViewContext(), LoginRequest(email, password),this)
    }

    override fun onLoginSuccess(response: LoginResponse?) {
        if (view.isActive()) {
            view.hideProgress()
            view.onLoginSuccess()
        }
    }

    override fun onLoginFailure() {
        if (view.isActive()) {
            view.hideProgress()
            view.onLoginFailure()
        }
    }

    override fun onNetworkError() {
        if (view.isActive()) {
            view.hideProgress()
            view.showAlert(R.string.error_network)
        }
    }

}