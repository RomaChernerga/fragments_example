package com.example.mvp_mvvm_new.ui.login

import com.example.mvp_mvvm_new.domain.LoginUsecase
import com.example.mvp_mvvm_new.utils.Publisher

class LoginViewModel(
    private val loginUsecase: LoginUsecase
    ): LoginContracts.ViewModel {

    override val shouldShowProgress: Publisher<Boolean> = Publisher()

    override val isSuccess: Publisher<Boolean> = Publisher()

    override val errorText: Publisher<String?>  = Publisher(true)

//    private var view: LoginContracts.View? = null
//    private var isSuccess: Boolean = false
//    private var errorText: String = ""

//    override fun onAttach(view: LoginContracts.View) {
//        this.view = view
//        if(isSuccess) {
//            view.setSuccess()
//        }
//    }

    override fun onLogin(login: String, password: String) {
//        view?.showProgress()
        shouldShowProgress.post(true)

        loginUsecase.login(login, password) { result ->
//            view?.hideProgress()
            shouldShowProgress.post(false)
            if(result) {
//                view?.setSuccess()
                isSuccess.post(true)
//                isSuccess = true
//                errorText = ""
                errorText.post("")
            } else {
//                view?.setError("Неверный пароль")
                isSuccess.post(false)
                errorText.post("Неверный пароль")
//                errorText = "Ошибка пароля"
            }
        }
    }

    override fun onCredentialsChanges() {
        TODO("Not yet implemented")
    }
}