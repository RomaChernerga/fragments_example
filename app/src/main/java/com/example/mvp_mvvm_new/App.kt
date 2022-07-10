package com.example.mvp_mvvm_new

import android.app.Application
import android.content.Context
import com.example.mvp_mvvm_new.data.LoginUsecaseImpl
import com.example.mvp_mvvm_new.data.api.MockLoginApiImpl
import com.example.mvp_mvvm_new.domain.LoginApi
import com.example.mvp_mvvm_new.domain.LoginUsecase

class App: Application() {
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
//    private val handler = Handler(Looper.getMainLooper())
    val loginUseCase: LoginUsecase by lazy { LoginUsecaseImpl(app.loginApi) }
}

val Context.app : App
    get() {
        return applicationContext as App
    }