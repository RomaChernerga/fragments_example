package com.example.mvp_mvvm_new.domain

import androidx.annotation.WorkerThread

@WorkerThread
interface LoginApi {
    fun login(login: String, password: String): Boolean
    fun register(login: String, password: String, email: String): Boolean
    fun logOut(): Boolean
    fun forgotPassword(login: String): Boolean
}