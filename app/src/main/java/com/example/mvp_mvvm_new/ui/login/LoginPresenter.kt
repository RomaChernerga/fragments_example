package com.example.mvp_mvvm_new.ui.login

import com.example.mvp_mvvm_new.domain.LoginUsecase

class LoginPresenter(
    private val loginUsecase: LoginUsecase
    ): LoginContracts.Presenter {

    private var view: LoginContracts.View? = null
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: LoginContracts.View) {
        this.view = view
        if(isSuccess) {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()

        loginUsecase.login(login, password) { result ->
            view?.hideProgress()
            if(result) {
                view?.setSuccess()
                isSuccess = true
                errorText = ""
            } else {
                view?.setError("Неверный пароль")
                isSuccess = false
                errorText = "Ошибка пароля"
            }
        }
    }

    override fun onCredentialsChanges() {
        TODO("Not yet implemented")
    }
}