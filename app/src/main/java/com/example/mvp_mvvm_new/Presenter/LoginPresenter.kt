package com.example.mvp_mvvm_new.Presenter

import android.os.Handler
import android.os.Looper
import com.example.mvp_mvvm_new.LoginContracts
import java.lang.Thread.sleep

class LoginPresenter: LoginContracts.Presenter {

    private var view: LoginContracts.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: LoginContracts.View) {
        this.view = view
        if(isSuccess) {
            view.setSuccess()
        } else {
            view.setError(errorText)
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3_000)
            uiHandler.post{
                view?.hideProgress()
                if(checkCredentials(login, password)) {
                    view?.setSuccess()
                    isSuccess = true
                    errorText = ""
                } else {
                    view?.setError("Неверный пароль")
                    isSuccess = false
                    errorText = "Ошибка пароля"
                }
            }

        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return login == password
    }

    override fun onCredentialsChanges() {
        TODO("Not yet implemented")
    }
}