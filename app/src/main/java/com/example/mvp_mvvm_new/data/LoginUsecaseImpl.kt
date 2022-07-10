package com.example.mvp_mvvm_new.data

import androidx.annotation.MainThread
import com.example.mvp_mvvm_new.domain.LoginApi
import com.example.mvp_mvvm_new.domain.LoginUsecase

class LoginUsecaseImpl(
    private val api: LoginApi,
//    private val uiHandler: android.os.Handler
    ): LoginUsecase {

    override fun login(
        login: String,
        password: String,
        @MainThread callBack: (Boolean) -> Unit) {

        Thread {
            val result = api.login(login, password)
            callBack(result)
//            uiHandler.post {
//            }
        }.start()

    }
}