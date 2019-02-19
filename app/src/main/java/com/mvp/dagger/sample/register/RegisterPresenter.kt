package com.mvp.dagger.sample.register

import com.mvp.dagger.sample.R
import com.mvp.dagger.sample.data.user.UserRepository
import com.mvp.dagger.sample.webservice.RegisterRequest
import com.mvp.dagger.sample.webservice.RegisterResponse
import javax.inject.Inject

class RegisterPresenter @Inject constructor(private val view: IRegisterContract.IRegisterView): IRegisterContract.IRegisterPresenter, UserRepository.IRegisterListener {

    override fun isValidName(name: String): Boolean = name.isNotEmpty()

    override fun isValidEmail(email: String): Boolean = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun isValidPassword(password: String): Boolean = password.isNotEmpty()

    override fun isValidForm(name: String, email: String, password: String): Boolean = isValidName(name) && isValidEmail(email) && isValidPassword(password)

    override fun register(name: String, email: String, password: String) {
        view.showProgress()
        UserRepository.getInstance().register(view.getViewContext(), RegisterRequest(name, email, password),this)
    }

    override fun onRegisterSuccess(response: RegisterResponse?) {
        if (view.isActive()) {
            view.hideProgress()
            view.onRegisterSuccess()
        }
    }

    override fun onRegisterFailure() {
        if (view.isActive()) {
            view.hideProgress()
            view.onRegisterFailure()
        }
    }

    override fun onNetworkError() {
        if (view.isActive()) {
            view.hideProgress()
            view.showAlert(R.string.error_network)
        }
    }

}