package com.example.mvp_mvvm_new.data

import com.example.mvp_mvvm_new.domain.LoginApi

class MockLoginApiImpl: LoginApi {
    override fun login(login: String, password: String): Boolean {
        Thread.sleep(3_000)
        return login == password
    }

    override fun register(login: String, password: String, email: String): Boolean {
        Thread.sleep(2_000)
        return login.isNotEmpty()
    }

    override fun logOut(): Boolean {
        Thread.sleep(2_000)
        return true
    }

    override fun forgotPassword(login: String): Boolean {
        Thread.sleep(1_000)
        return false
    }
}