package com.example.mvp_mvvm_new.data

import com.example.mvp_mvvm_new.domain.LoginApi

class WebLoginApiImpl: LoginApi {
    override fun login(login: String, password: String): Boolean {

        TODO("Not yet implemented")
    }

    override fun register(login: String, password: String, email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logOut(): Boolean {
        TODO("Not yet implemented")
    }

    override fun forgotPassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}