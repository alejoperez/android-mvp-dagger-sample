package com.mvp.dagger.sample.register

import android.os.Bundle
import android.view.View
import com.mvp.dagger.sample.R
import com.mvp.dagger.sample.base.BaseActivity
import com.mvp.dagger.sample.extensions.getWhiteSpaceFilters
import com.mvp.dagger.sample.login.LoginActivity
import com.mvp.dagger.sample.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class RegisterActivity : BaseActivity(), IRegisterContract.IRegisterView {

    @Inject
    lateinit var presenter: IRegisterContract.IRegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etEmail.filters = getWhiteSpaceFilters()
        etPassword.filters = getWhiteSpaceFilters()

        btRegister.setOnClickListener {
            onRegisterClicked()
        }

        tvGoToLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }

    private fun onRegisterClicked() {
        if (presenter.isValidForm(getName(), getEmail(), getPassword())) {
            presenter.register(getName(), getEmail(), getPassword())
        } else {
            if (!presenter.isValidName(getName())) {
                etName.error = getString(R.string.error_name_empty)
            }
            if (!presenter.isValidEmail(getEmail())) {
                etEmail.error = getString(R.string.error_invalid_email)
            }
            if (!presenter.isValidPassword(getPassword())) {
                etPassword.error = getString(R.string.error_empty_password)
            }
        }
    }

    override fun getName(): String = etName.text.toString()

    override fun getEmail(): String = etEmail.text.toString()

    override fun getPassword(): String = etPassword.text.toString()


    override fun onRegisterSuccess() {
        startActivity<MainActivity>()
        finishAffinity()
    }

    override fun onRegisterFailure() {
        showAlert(R.string.error_user_already_exists)
    }


    override fun showProgress() {
        btRegister.visibility = View.INVISIBLE
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        btRegister.visibility = View.VISIBLE
        progress.visibility = View.INVISIBLE
    }
}
